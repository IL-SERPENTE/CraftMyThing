package fr.blueslime.craftmything.events;

import fr.blueslime.craftmything.CraftMyThing;
import fr.blueslime.craftmything.arena.Arena;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CMTInventoryClickEvent extends AbstractEvent<InventoryClickEvent>
{
    public CMTInventoryClickEvent(CraftMyThing plugin, Arena arena)
    {
        super(plugin, arena);
    }

    public void event(InventoryClickEvent event)
    {
        if(event.getCurrentItem().getType() == Material.BARRIER)
            event.setCancelled(true);
    }
}
