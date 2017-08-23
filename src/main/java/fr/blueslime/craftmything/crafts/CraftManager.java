package fr.blueslime.craftmything.crafts;

import fr.blueslime.craftmything.CraftMyThing;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.ChatColor.*;

import java.util.ArrayList;
import java.util.Random;

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
public class CraftManager
{
    private final ArrayList<Craft> crafts;

    public CraftManager()
    {
        this.crafts = new ArrayList<>();

        this.registerCraft(new Craft("Table de Craft", new ItemStack(Material.WORKBENCH, 0), EnumZone.FOREST));
    }

    public void registerCraft(Craft craft)
    {
        this.crafts.add(craft);
        CraftMyThing.getInstance().getLogger().info("Registered craft: " + craft.getName());
    }

    public Craft randomCraft(EnumZone zone)
    {
        ArrayList<Craft> temp = new ArrayList<>();

        for(Craft craft : this.crafts)
            if(craft.getZone() == zone)
                temp.add(craft);

        return temp.get(new Random().nextInt(temp.size()));
    }
}
