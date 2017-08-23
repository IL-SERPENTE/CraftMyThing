package fr.blueslime.craftmything;

import net.samagames.api.SamaGamesAPI;
import org.bukkit.ChatColor;

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
public enum Messages
{
    disclaimer(ChatColor.RED + "Pour valider un craft, vous devez le donner au Dieu des Chats !", true),
    validCraft(ChatColor.GREEN + "${PLAYER} à réussi le craft et est sélectionné pour la manche suivante !", true),
    eliminated(ChatColor.RED + "${PLAYER} est éliminé car il n'a pas effectué le craft à temps !", true),
    dead(ChatColor.RED + "${PLAYER} est éliminé car il est mort !", true);

    private String message;
    private boolean tag;

    Messages(String message, boolean tag)
    {
        this.message = message;
        this.tag = tag;
    }

    @Override
    public String toString()
    {
        return (this.tag ? SamaGamesAPI.get().getGameManager().getCoherenceMachine().getGameTag() + " " : "") + this.message;
    }
}
