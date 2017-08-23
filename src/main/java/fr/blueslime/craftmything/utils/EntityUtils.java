package fr.blueslime.craftmything.utils;


import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

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
public class EntityUtils
{
    public static ArrayList<Entity> getNearbyEntities(Location center, double radius)
    {
        return getNearbyEntities(center, radius, null);
    }

    public static ArrayList<Entity> getNearbyEntities(Location center, double radius, EntityType filter)
    {
        double chunkRadius = radius < 16 ? 1 : (radius - (radius % 16)) / 16;
        ArrayList<Entity> entities = new ArrayList<>();

        for (double chX = 0 - chunkRadius; chX <= chunkRadius; chX++)
        {
            for (double chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++)
            {
                double x = center.getX();
                double y = center.getY();
                double z = center.getZ();

                for (Entity entity : new Location(center.getWorld(), x + (chX * 16), y, z + (chZ * 16)).getChunk().getEntities())
                {
                    if (filter != null)
                    {
                        if(entity.getType() != filter)
                            continue;
                    }

                    if (entity.getLocation().distance(center) <= radius && entity.getLocation().getBlock() != center.getBlock())
                        entities.add(entity);
                }
            }
        }

        return entities;
    }
}