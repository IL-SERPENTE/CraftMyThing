package fr.blueslime.craftmything.crafts;

import org.bukkit.ChatColor;
import org.bukkit.Color;

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
public enum EnumZone
{
    FOREST(ChatColor.GREEN + "Forêt", Color.GREEN, Color.LIME),
    DESERT(ChatColor.YELLOW + "Desert", Color.ORANGE, Color.YELLOW),
    UNDERGROUND(ChatColor.LIGHT_PURPLE + "Sous-terrain", Color.PURPLE, Color.FUCHSIA),
    NETHER(ChatColor.RED + "Enfers", Color.RED, Color.ORANGE),
    END(ChatColor.AQUA + "Néant", Color.AQUA, Color.SILVER);

    private final String displayName;
    private final Color color;
    private final Color fade;

    EnumZone(String displayName, Color color, Color fade)
    {
        this.displayName = displayName;
        this.color = color;
        this.fade = fade;
    }

    public Color getColor()
    {
        return this.color;
    }

    public Color getFade()
    {
        return this.fade;
    }

    @Override
    public String toString()
    {
        return this.displayName;
    }
}
