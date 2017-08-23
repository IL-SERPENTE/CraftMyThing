package fr.blueslime.craftmything.arena;

import net.samagames.api.games.GamePlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/*
 * This file is part of CraftMyThing.
 *
 * CraftMyThing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CraftMyThing is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CraftMyThing.  If not, see <http://www.gnu.org/licenses/>.
 */
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
