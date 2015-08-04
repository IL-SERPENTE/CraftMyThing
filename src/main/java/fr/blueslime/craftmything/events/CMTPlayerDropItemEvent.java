package fr.blueslime.craftmything.events;

import fr.blueslime.craftmything.CraftMyThing;
import fr.blueslime.craftmything.arena.Arena;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class CMTPlayerDropItemEvent extends AbstractEvent<PlayerDropItemEvent>
{
    public CMTPlayerDropItemEvent(CraftMyThing plugin, Arena arena)
    {
        super(plugin, arena);
    }

    public void event(PlayerDropItemEvent event)
    {
        event.getItemDrop().setMetadata("dropped-by", new FixedMetadataValue(CraftMyThing.getInstance(), event.getPlayer().getUniqueId().toString()));
    }
}
