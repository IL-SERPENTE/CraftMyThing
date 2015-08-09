package fr.blueslime.craftmything;

import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;
import fr.blueslime.craftmything.arena.Arena;
import fr.blueslime.craftmything.arena.ArenaManager;
import fr.blueslime.craftmything.arena.GodOfMeow;
import fr.blueslime.craftmything.events.*;
import net.minecraft.server.v1_8_R3.*;
import net.samagames.api.SamaGamesAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class CraftMyThing extends JavaPlugin
{
    private static CraftMyThing instance;
    private EffectManager effectManager;
    private Arena arena;

    @Override
    public void onEnable()
    {
        instance = this;

        this.effectManager = new EffectManager(this.getEffectLib());

        this.registerEntity("GodOfMeow", 98, EntityOcelot.class, GodOfMeow.class);
        this.arena = new ArenaManager().loadArena();
        this.registerEvents();

        SamaGamesAPI.get().getGameManager().registerGame(this.arena);
    }

    public void registerEvents()
    {
        Bukkit.getPluginManager().registerEvents(new CMTBlockDamageEvent(this, this.arena), this);
        Bukkit.getPluginManager().registerEvents(new CMTEntityDamageByEntityEvent(this, this.arena), this);
        Bukkit.getPluginManager().registerEvents(new CMTEntityDamageEvent(this, this.arena), this);
        Bukkit.getPluginManager().registerEvents(new CMTEntityDeathEvent(this, this.arena), this);
        Bukkit.getPluginManager().registerEvents(new CMTInventoryClickEvent(this, this.arena), this);
        Bukkit.getPluginManager().registerEvents(new CMTInventoryOpenEvent(this, this.arena), this);
        Bukkit.getPluginManager().registerEvents(new CMTPlayerDropItemEvent(this, this.arena), this);
    }

    public void registerEntity(String name, int id, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass)
    {
        BiomeBase[] biomes;

        try
        {
            biomes = (BiomeBase[]) getPrivateStatic(BiomeBase.class, "biomes");
            this.registerEntityInEntityEnum(customClass, name, id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        for (BiomeBase biomeBase : biomes)
        {
            if (biomeBase == null)
                continue;

            for (String field : new String[]{"at", "au", "av", "aw"})
            {
                try
                {
                    Field list = BiomeBase.class.getDeclaredField(field);
                    list.setAccessible(true);
                    List<BiomeBase.BiomeMeta> mobList = (List<BiomeBase.BiomeMeta>) list.get(biomeBase);

                    mobList.stream().filter(meta -> nmsClass.equals(meta.b)).forEach(meta -> meta.b = customClass);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void registerEntityInEntityEnum(Class paramClass, String paramString, int paramInt) throws Exception
    {
        ((Map) this.getPrivateStatic(EntityTypes.class, "c")).put(paramString, paramClass);
        ((Map) this.getPrivateStatic(EntityTypes.class, "d")).put(paramClass, paramString);
        ((Map) this.getPrivateStatic(EntityTypes.class, "e")).put(paramInt, paramClass);
        ((Map) this.getPrivateStatic(EntityTypes.class, "f")).put(paramClass, paramInt);
        ((Map) this.getPrivateStatic(EntityTypes.class, "g")).put(paramString, paramInt);
    }

    private Object getPrivateStatic(Class clazz, String f) throws Exception
    {
        Field field = clazz.getDeclaredField(f);
        field.setAccessible(true);

        return field.get(null);
    }

    public EffectManager getEffectManager()
    {
        return this.effectManager;
    }

    public Arena getArena()
    {
        return this.arena;
    }

    public EffectLib getEffectLib()
    {
        Plugin effectLib = Bukkit.getPluginManager().getPlugin("EffectLib");

        if (effectLib == null || !(effectLib instanceof EffectLib))
            return null;

        return (EffectLib) effectLib;
    }

    public static CraftMyThing getInstance()
    {
        return instance;
    }
}
