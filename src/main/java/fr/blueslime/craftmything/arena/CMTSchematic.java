package fr.blueslime.craftmything.arena;

import fr.blueslime.craftmything.crafts.EnumZone;
import net.samagames.api.schematics.ISchematic;
import net.samagames.api.schematics.Schematics;
import org.bukkit.Material;

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
public class CMTSchematic
{
    private final ISchematic schematic;
    private final EnumZone zone;
    private final ArrayList<Material> blacklist;

    public CMTSchematic(ISchematic schematic, EnumZone zone, ArrayList<Material> blacklist)
    {
        this.schematic = schematic;
        this.zone = zone;
        this.blacklist = blacklist;
    }

    public ISchematic getSchematic()
    {
        return this.schematic;
    }

    public EnumZone getZone()
    {
        return this.zone;
    }

    public ArrayList<Material> getBlacklist()
    {
        return this.blacklist;
    }

    public boolean isBlacklisted(Material material)
    {
        return this.blacklist.contains(material);
    }
}
