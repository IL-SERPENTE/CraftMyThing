package fr.blueslime.craftmything.events;

import fr.blueslime.craftmything.CraftMyThing;
import fr.blueslime.craftmything.arena.Arena;
import fr.blueslime.craftmything.crafts.EnumChildren;
import org.bukkit.event.block.BlockDamageEvent;

public class CMTBlockDamageEvent extends AbstractEvent<BlockDamageEvent>
{
    public CMTBlockDamageEvent(CraftMyThing plugin, Arena arena)
    {
        super(plugin, arena);
    }

    public void event(BlockDamageEvent event)
    {
        if(this.arena.getActualSchematic() != null)
        {
            if(this.arena.getActualSchematic().isBlacklisted(event.getBlock().getType()))
            {
                event.setCancelled(true);
                return;
            }
        }

        if(EnumChildren.hasChild(event.getBlock().getType()))
        {
            event.setCancelled(true);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), EnumChildren.getChild(event.getBlock().getType()));
        }
        else
        {
            event.setInstaBreak(true);
        }
    }
}
