package fr.blueslime.craftmything.events;

import fr.blueslime.craftmything.CraftMyThing;
import fr.blueslime.craftmything.arena.Arena;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

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
