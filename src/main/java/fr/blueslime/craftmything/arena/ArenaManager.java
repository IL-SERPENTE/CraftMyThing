package fr.blueslime.craftmything.arena;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.blueslime.craftmything.crafts.EnumZone;
import net.samagames.api.SamaGamesAPI;
import net.samagames.tools.LocationUtils;
import org.bukkit.Location;

import java.util.ArrayList;

public class ArenaManager
{
    public Arena loadArena()
    {
        JsonObject jsonArena = SamaGamesAPI.get().getGameManager().getGameProperties().getMapProperties();

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
}
