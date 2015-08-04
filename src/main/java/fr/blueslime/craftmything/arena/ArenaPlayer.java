package fr.blueslime.craftmything.arena;

import net.samagames.api.games.GamePlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ArenaPlayer extends GamePlayer
{
    private Location spawn;

    public ArenaPlayer(Player player)
    {
        super(player);
    }

    public void setSpawn(Location spawn)
    {
        this.spawn = spawn;
    }

    public Location getSpawn()
    {
        return this.spawn;
    }
}
