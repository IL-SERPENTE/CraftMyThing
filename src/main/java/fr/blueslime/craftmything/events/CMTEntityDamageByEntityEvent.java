package fr.blueslime.craftmything.events;

import fr.blueslime.craftmything.CraftMyThing;
import fr.blueslime.craftmything.arena.Arena;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CMTEntityDamageByEntityEvent extends AbstractEvent<EntityDamageByEntityEvent>
{
    public CMTEntityDamageByEntityEvent(CraftMyThing plugin, Arena arena)
    {
        super(plugin, arena);
    }

    public void event(EntityDamageByEntityEvent event)
    {
        event.setCancelled(true);
    }
}
