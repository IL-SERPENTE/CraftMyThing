package fr.blueslime.craftmything.arena;

import fr.blueslime.craftmything.CraftMyThing;
import fr.blueslime.craftmything.Messages;
import fr.blueslime.craftmything.crafts.Craft;
import fr.blueslime.craftmything.crafts.CraftManager;
import fr.blueslime.craftmything.crafts.EnumZone;
import fr.blueslime.craftmything.utils.EntityUtils;
import net.samagames.api.SamaGamesAPI;
import net.samagames.api.games.Game;
import net.samagames.api.games.themachine.messages.templates.PlayerLeaderboardWinTemplate;
import net.samagames.tools.ColorUtils;
import net.samagames.tools.ParticleEffect;
import net.samagames.tools.scoreboards.ObjectiveSign;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Arena extends Game<ArenaPlayer>
{
    private final ArrayList<Location> spawns;
    private final ArrayList<EnumZone> floors;
    private final ArrayList<UUID> remaining;
    private final Location godSpawn;
    private final SchematicManager schematicManager;
    private final CraftManager craftManager;
    private final World world;
    private ObjectiveSign objective;
    private GodOfMeow godOfMeow;
    private CMTSchematic schematicSelected;
    private Craft craftSelected;
    private Player lastCrafter;
    private Player second;
    private Player third;
    private int wave;

    public Arena(Location godSpawn, ArrayList<Location> spawns, ArrayList<EnumZone> floors)
    {
        super("arcade", "CraftMyThing", ArenaPlayer.class);

        this.remaining = new ArrayList<>();
        this.schematicManager = new SchematicManager();
        this.craftManager = new CraftManager();
        this.spawns = spawns;
        this.floors = floors;
        this.godSpawn = godSpawn;
        this.world = godSpawn.getWorld();
        this.wave = 0;

        this.godOfMeow = null;

        this.objective = new ObjectiveSign("craftmything", ChatColor.GREEN + "" + ChatColor.BOLD + "CraftMyThing" + ChatColor.WHITE + " | " + ChatColor.AQUA + "00:00");
    }

    @Override
    public void startGame()
    {
        super.startGame();

        for(ArenaPlayer player : this.getInGamePlayers().values())
        {
            player.getPlayerIfOnline().setGameMode(GameMode.SURVIVAL);
            this.increaseStat(player.getUUID(), "played_games", 1);
        }

        this.teleportGodAt(this.godSpawn);
        Bukkit.broadcastMessage(Messages.disclaimer.toString());
        this.nextWave();

        Bukkit.getScheduler().runTaskTimerAsynchronously(CraftMyThing.getInstance(), new Runnable() {
            private int time = 0;

            @Override
            public void run() {
                this.time++;
                objective.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "CraftMyThing" + ChatColor.WHITE + " | " + ChatColor.AQUA + this.formatTime(this.time));
                updateScoreboard();
            }

            public String formatTime(int time) {
                int mins = time / 60;
                int remainder = time - mins * 60;
                int secs = remainder;

                String secsSTR = (secs < 10) ? "0" + secs : secs + "";

                return mins + ":" + secsSTR;
            }
        }, 0L, 20L);

        Bukkit.getScheduler().runTaskTimer(CraftMyThing.getInstance(), () ->
        {
            Location blackHoleLocation = this.godSpawn;

            for (Entity entity : EntityUtils.getNearbyEntities(blackHoleLocation, 12, EntityType.DROPPED_ITEM)) {
                Item item = (Item) entity;

                if (!item.hasMetadata("dropped-by"))
                    continue;

                Vector entityVector = new Vector(item.getLocation().getX(), item.getLocation().getY(), item.getLocation().getZ());
                Vector blackholeVector = new Vector(blackHoleLocation.getX(), blackHoleLocation.getY(), blackHoleLocation.getZ());
                item.setVelocity(blackholeVector.subtract(entityVector).normalize().multiply(0.25F));

                if (item.getLocation().distanceSquared(blackHoleLocation) <= 1.0D)
                    if (!this.receiveCraft(Bukkit.getPlayer(UUID.fromString(item.getMetadata("dropped-by").get(0).asString())), item.getItemStack()))
                        item.remove();
            }
        }, 1L, 1L);
    }

    @Override
    public void handleLogin(Player player)
    {
        super.handleLogin(player);

        this.getPlayer(player.getUniqueId()).setSpawn(this.spawns.get(0));
        this.spawns.remove(0);

        this.objective.addReceiver(player);
        this.setupPlayer(player);

        player.getInventory().setItem(8, this.coherenceMachine.getLeaveItem());
    }

    @Override
    public void handleLogout(Player player)
    {
        super.handleLogout(player);

        this.setSpectator(player);
        this.checkEnd(player);
    }

    public void win(ArenaPlayer player)
    {
        PlayerLeaderboardWinTemplate template = SamaGamesAPI.get().getGameManager().getCoherenceMachine().getTemplateManager().getPlayerLeaderboardWinTemplate();
        template.execute(player.getPlayerIfOnline(), this.second, this.third);

        this.addCoins(player.getPlayerIfOnline(), 50, "Premier");
        this.addCoins(this.second, 25, "Second");
        this.addCoins(this.third, 10, "Troisième");

        this.addStars(player.getPlayerIfOnline(), 2, "Victoire");
        this.increaseStat(player.getUUID(), "wins", 1);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(CraftMyThing.getInstance(), new Runnable()
        {
            int number = (int) (10 * 1.5);
            int count = 0;

            public void run()
            {
                if (this.count >= this.number || player.getPlayerIfOnline() == null)
                    return;

                Firework fw = (Firework) player.getPlayerIfOnline().getWorld().spawnEntity(player.getPlayerIfOnline().getLocation(), EntityType.FIREWORK);
                FireworkMeta fwm = fw.getFireworkMeta();

                Random r = new Random();

                int rt = r.nextInt(4) + 1;
                FireworkEffect.Type type = FireworkEffect.Type.BALL;
                if (rt == 1) type = FireworkEffect.Type.BALL;
                if (rt == 2) type = FireworkEffect.Type.BALL_LARGE;
                if (rt == 3) type = FireworkEffect.Type.BURST;
                if (rt == 4) type = FireworkEffect.Type.CREEPER;
                if (rt == 5) type = FireworkEffect.Type.STAR;

                int r1i = r.nextInt(17) + 1;
                int r2i = r.nextInt(17) + 1;
                Color c1 = ColorUtils.getColor(r1i);
                Color c2 = ColorUtils.getColor(r2i);

                FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();

                fwm.addEffect(effect);

                int rp = r.nextInt(2) + 1;
                fwm.setPower(rp);

                fw.setFireworkMeta(fwm);

                this.count++;
            }
        }, 5L, 5L);

        this.handleGameEnd();
    }

    public void lose(Player player)
    {
        this.setSpectator(player);

        Bukkit.broadcastMessage(Messages.dead.toString().replace("${PLAYER}", player.getName()));

        this.checkEnd(player);
    }

    public boolean receiveCraft(Player player, ItemStack stack)
    {
        if(!stack.isSimilar(this.craftSelected.getItem()))
            return false;

        player.getInventory().clear();

        Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);

        FireworkMeta fwm = fw.getFireworkMeta();
        FireworkEffect effect = FireworkEffect.builder().withFlicker().withColor(this.getActualZoneType().getColor()).withFade(this.getActualZoneType().getFade()).with(FireworkEffect.Type.STAR).build();
        fwm.addEffect(effect);
        fwm.setPower(2);

        fw.setFireworkMeta(fwm);

        Bukkit.broadcastMessage(Messages.validCraft.toString().replace("${PLAYER}", player.getName()));

        this.remaining.remove(player.getUniqueId());
        this.lastCrafter = player;
        this.checkEnd(player);

        return true;
    }

    public void checkEnd(Player lose)
    {
        if(this.remaining.size() == 1)
        {
            this.second = lose;

            Player last = Bukkit.getPlayer(this.remaining.get(0));
            Bukkit.broadcastMessage(Messages.eliminated.toString().replace("${PLAYER}", last.getName()));

            last.getWorld().createExplosion(last.getLocation().getX(), last.getLocation().getY(), last.getLocation().getZ(), 2.0F, false, false);

            this.setSpectator(last);

            if(this.wave == 7)
                this.win(this.getPlayer(this.lastCrafter.getUniqueId()));
            else
                this.nextWave();
        }
        else if(this.remaining.size() == 2)
        {
            this.third = lose;
        }
    }

    public void setupPlayer(Player player)
    {
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20.0D);
        player.setSaturation(20);
        player.getInventory().clear();
        player.setExp(0.0F);
        player.setLevel(0);

        for(PotionEffect pe : player.getActivePotionEffects())
            player.removePotionEffect(pe.getType());
    }

    public void updateScoreboard()
    {
        this.objective.setLine(0, ChatColor.GRAY + "Niveau: " + ChatColor.WHITE + this.wave);
        this.objective.setLine(1, ChatColor.WHITE + "");
        this.objective.setLine(2, ChatColor.GRAY + "Joueurs: " + ChatColor.WHITE + this.remaining.size());
        this.objective.setLine(3, ChatColor.GRAY + "");
        this.objective.setLine(4, ChatColor.GRAY + "Environement: " + this.getActualZoneType().toString());
        this.objective.setLine(5, ChatColor.GRAY + "Craft: " + ChatColor.WHITE + this.craftSelected.getName());

        this.objective.updateLines();
    }

    public void nextWave()
    {
        this.wave++;

        this.remaining.clear();
        this.remaining.addAll(this.getInGamePlayers().keySet());

        if(this.schematicSelected != null)
            this.schematicSelected.getSchematic().fill(this.godSpawn.clone().subtract(0.0D, 15.0D, 0.0D));

        for(ArenaPlayer gamePlayer : this.getInGamePlayers().values())
        {
            this.world.getBlockAt(gamePlayer.getSpawn().clone().subtract(0.0D, 1.0D, 0.0D)).setType(Material.BARRIER);
            gamePlayer.getPlayerIfOnline().teleport(gamePlayer.getSpawn());
        }

        this.schematicSelected = this.schematicManager.randomSchematic(this.schematicSelected);
        this.schematicSelected.getSchematic().paste(this.godSpawn.clone().subtract(0.0D, 15.0D, 0.0D));

        Bukkit.getScheduler().runTaskLaterAsynchronously(CraftMyThing.getInstance(), () ->
        {
            for(ArenaPlayer gamePlayer : this.getInGamePlayers().values())
                this.world.getBlockAt(gamePlayer.getSpawn().clone().subtract(0.0D, 1.0D, 0.0D)).setType(Material.AIR);

            this.craftSelected = this.craftManager.randomCraft(this.getActualZoneType());

            for(ArenaPlayer gamePlayer : this.getInGamePlayers().values())
            {
                Player player = gamePlayer.getPlayerIfOnline();

                for(int i = 9; i < player.getInventory().getSize(); i++)
                {
                    player.getInventory().setItem(i, new ItemStack(Material.BARRIER, 1));
                }
            }
        }, 20L * 5);
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
            if (this.godOfMeow == null) {
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

    public EnumZone getActualZoneType()
    {
        return this.floors.get((this.wave - 1));
    }

    public CMTSchematic getActualSchematic()
    {
        return this.schematicSelected;
    }
}
