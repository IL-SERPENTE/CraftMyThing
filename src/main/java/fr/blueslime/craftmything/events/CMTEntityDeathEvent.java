package fr.blueslime.craftmything.events;

import fr.blueslime.craftmything.CraftMyThing;
import fr.blueslime.craftmything.arena.Arena;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

public class CMTEntityDeathEvent extends AbstractEvent<EntityDeathEvent>
{
    public CMTEntityDeathEvent(CraftMyThing plugin, Arena arena)
    {
        super(plugin, arena);
    }

    public void event(EntityDeathEvent event)
    {
        if(event.getEntityType() == EntityType.PLAYER)
        {
            this.arena.lose((Player) event.getEntity());
        }
    }
}
