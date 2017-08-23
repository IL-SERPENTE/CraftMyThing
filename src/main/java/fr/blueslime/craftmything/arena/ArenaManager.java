package fr.blueslime.craftmything.arena;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.blueslime.craftmything.crafts.EnumZone;
import net.samagames.api.SamaGamesAPI;
import net.samagames.tools.LocationUtils;
import org.bukkit.Location;

import java.util.ArrayList;

/*
 * This file is part of CraftMyThing.
 *
 * CraftMyThing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CraftMyThing is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CraftMyThing.  If not, see <http://www.gnu.org/licenses/>.
 */
public class ArenaManager
{
    public Arena loadArena()
    {
        JsonObject jsonArena = SamaGamesAPI.get().getGameManager().getGameProperties().getConfigs();

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
