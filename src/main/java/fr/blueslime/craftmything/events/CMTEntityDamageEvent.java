package fr.blueslime.craftmything.events;

import fr.blueslime.craftmything.CraftMyThing;
import fr.blueslime.craftmything.arena.Arena;
import org.bukkit.event.entity.EntityDamageEvent;

public class CMTEntityDamageEvent extends AbstractEvent<EntityDamageEvent>
{
    public CMTEntityDamageEvent(CraftMyThing plugin, Arena arena)
    {
        super(plugin, arena);
    }

    public void event(EntityDamageEvent event)
    {
        event.setCancelled(true);

        if(event.getCause() == EntityDamageEvent.DamageCause.FIRE || event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK || event.getCause() == EntityDamageEvent.DamageCause.VOID)
        {
            event.setCancelled(false);
        }
    }
}
