package fr.blueslime.craftmything.crafts;

import org.bukkit.ChatColor;
import org.bukkit.Color;

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
