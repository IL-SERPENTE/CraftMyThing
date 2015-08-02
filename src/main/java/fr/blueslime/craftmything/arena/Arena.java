package fr.blueslime.craftmything.arena;

import fr.blueslime.craftmything.CraftMyThing;
import fr.blueslime.craftmything.crafts.Craft;
import fr.blueslime.craftmything.crafts.CraftManager;
import fr.blueslime.craftmything.crafts.EnumZone;
import net.samagames.api.games.Game;
import net.samagames.api.games.GamePlayer;
import net.samagames.tools.ParticleEffect;
import net.samagames.tools.scoreboards.ObjectiveSign;
import org.bukkit.*;
import org.bukkit.entity.Ocelot;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Arena extends Game<GamePlayer>
{
    private final ArrayList<Location> spawns;
    private final ArrayList<EnumZone> floors;
    private final Location godSpawn;
    private final CraftManager craftManager;
    private final World world;
    private ObjectiveSign objective;
    private GodOfMeow godOfMeow;
    private Craft craftSelected;
    private int highLevel;
    private int wave;

    public Arena(Location godSpawn, ArrayList<Location> spawns, ArrayList<EnumZone> floors)
    {
        super("arcade", "CraftMyThing", GamePlayer.class);

        this.craftManager = new CraftManager();
        this.spawns = spawns;
        this.floors = floors;
        this.godSpawn = godSpawn;
        this.world = godSpawn.getWorld();
        this.highLevel = 230;
        this.wave = 1;

        this.godOfMeow = null;

        this.objective = new ObjectiveSign("craftmything", ChatColor.GREEN + "" + ChatColor.BOLD + "CraftMyThing" + ChatColor.WHITE + " | " + ChatColor.AQUA + "00:00");
    }

    @Override
    public void startGame()
    {
        super.startGame();

        this.teleportGodAt(this.godSpawn);

        Bukkit.getScheduler().runTaskTimerAsynchronously(CraftMyThing.getInstance(), new Runnable()
        {
            private int time = 0;

            @Override
            public void run()
            {
                this.time++;
                objective.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "CraftMyThing" + ChatColor.WHITE + " | " + ChatColor.AQUA + this.formatTime(this.time));
            }

            public String formatTime(int time)
            {
                int mins = time / 60;
                int remainder = time - mins * 60;
                int secs = remainder;

                String secsSTR = (secs < 10) ? "0" + secs : secs + "";

                return mins + ":" + secsSTR;
            }
        }, 0L, 20L);
    }

    public void nextWave()
    {
        Location min = this.godSpawn.clone().subtract(250.0D, 0.0D, 250.0D);
        Location max = this.godSpawn.clone().add(250.0D, 0.0D, 250.0D);

        for(int x = min.getBlockX(); x <= max.getBlockX(); x++)
        {
            for(int z = min.getBlockZ(); z <= max.getBlockZ(); z++)
            {
                for(int y = this.highLevel; y <= (this.highLevel + 20); y++)
                {
                    this.world.getBlockAt(x, y, z).setType(Material.AIR);
                }
            }
        }

        this.wave++;
        this.highLevel -= 20;

        this.craftSelected = this.craftManager.randomCraft(this.floors.get((this.wave - 1)));
        this.teleportGodAt(this.godSpawn.clone().subtract(0.0D, ((this.wave - 1) * 20), 0.0D));
    }

    public void teleportGodAt(Location at)
    {
        new BukkitRunnable()
        {
            int ticks = 0;

            @Override
            public void run()
            {
                ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(0, 0, 0), at, 128.0D);

                this.ticks += 2;

                if(this.ticks == (20 * 3))
                    this.cancel();
            }
        }.runTaskTimerAsynchronously(CraftMyThing.getInstance(), 2L, 2L);

        Bukkit.getScheduler().runTaskLaterAsynchronously(CraftMyThing.getInstance(), () ->
        {
            if (this.godOfMeow == null)
            {
                this.world.createExplosion(at.getX(), at.getY(), at.getZ(), 10.0F, false, false);
                this.world.playSound(at, Sound.WITHER_DEATH, 1.0F, 12.0F);

                this.godOfMeow = new GodOfMeow(((CraftWorld) this.world).getHandle(), at);
                ((Ocelot) this.godOfMeow.getBukkitEntity()).setCatType(Ocelot.Type.SIAMESE_CAT);

                return;
            }

            this.godOfMeow.teleportTo(at, false);
            this.godOfMeow.getCubeEffect().setLocation(at.clone().subtract(0.0D, 5.0D, 0.0D));

            this.world.playSound(at, Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
        }, 20L * 3);
    }
}
