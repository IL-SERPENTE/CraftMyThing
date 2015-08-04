package fr.blueslime.craftmything.arena;

import fr.blueslime.craftmything.crafts.EnumZone;
import net.samagames.api.schematics.ISchematic;
import net.samagames.api.schematics.Schematics;
import org.bukkit.Material;

import java.util.ArrayList;

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
