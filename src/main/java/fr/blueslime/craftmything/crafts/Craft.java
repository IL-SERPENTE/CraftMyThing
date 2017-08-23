package fr.blueslime.craftmything.crafts;

import org.bukkit.inventory.ItemStack;

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
public class Craft
{
    private final String name;
    private final ItemStack item;
    private final EnumZone zone;

    public Craft(String name, ItemStack item, EnumZone zone)
    {
        this.name = name;
        this.item = item;
        this.zone = zone;
    }

    public String getName()
    {
        return this.name;
    }

    public ItemStack getItem()
    {
        return this.item;
    }

    public EnumZone getZone()
    {
        return this.zone;
    }
}
