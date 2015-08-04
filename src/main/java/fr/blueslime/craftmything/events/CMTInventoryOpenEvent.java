package fr.blueslime.craftmything.events;

import fr.blueslime.craftmything.CraftMyThing;
import fr.blueslime.craftmything.arena.Arena;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class CMTInventoryOpenEvent extends AbstractEvent<InventoryOpenEvent>
{
    public CMTInventoryOpenEvent(CraftMyThing plugin, Arena arena)
    {
        super(plugin, arena);
    }

    public void event(InventoryOpenEvent event)
    {
        if(event.getView().getType() == InventoryType.PLAYER)
            event.setCancelled(true);
    }
}
