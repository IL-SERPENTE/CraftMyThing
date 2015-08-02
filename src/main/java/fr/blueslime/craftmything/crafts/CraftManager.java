package fr.blueslime.craftmything.crafts;

import fr.blueslime.craftmything.CraftMyThing;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.ChatColor.*;

import java.util.ArrayList;
import java.util.Random;

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
