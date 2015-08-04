package fr.blueslime.craftmything.events;

import fr.blueslime.craftmything.CraftMyThing;
import fr.blueslime.craftmything.arena.Arena;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public abstract class AbstractEvent<EVENT extends Event> implements Listener
{
    protected CraftMyThing plugin;
    protected Arena arena;

    public AbstractEvent(CraftMyThing plugin, Arena arena)
    {
        this.plugin = plugin;
        this.arena = arena;
    }

    @EventHandler
    public abstract void event(EVENT event);
}
