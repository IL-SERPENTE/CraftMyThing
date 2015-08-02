package fr.blueslime.craftmything.arena;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.blueslime.craftmything.crafts.EnumZone;
import net.samagames.tools.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class ArenaManager
{
    public Arena loadArena()
    {
        File worldFolder = Bukkit.getWorlds().get(0).getWorldFolder();
        File arenaFile = new File(worldFolder, "arena.json");

        try
        {
            JsonObject jsonArena = new JsonParser().parse(new FileReader(arenaFile)).getAsJsonObject();

            JsonArray jsonSpawns = jsonArena.get("spawns").getAsJsonArray();
            ArrayList<Location> spawns = new ArrayList<>();

            for(int i = 0; i < jsonSpawns.size(); i++)
                spawns.add(LocationUtils.str2loc(jsonSpawns.get(i).getAsString()));

            JsonArray jsonFloors = jsonArena.get("floors").getAsJsonArray();
            ArrayList<EnumZone> floors = new ArrayList<>();

            for(int i = 0; i < jsonFloors.size(); i++)
                floors.add(EnumZone.valueOf(jsonFloors.get(i).getAsString().toUpperCase()));

            return new Arena(LocationUtils.str2loc(jsonArena.get("god-location").getAsString()), spawns, floors);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
