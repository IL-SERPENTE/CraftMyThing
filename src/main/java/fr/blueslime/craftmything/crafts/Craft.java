package fr.blueslime.craftmything.crafts;

import org.bukkit.inventory.ItemStack;

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
