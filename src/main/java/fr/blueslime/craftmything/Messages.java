package fr.blueslime.craftmything;

import net.samagames.api.SamaGamesAPI;
import org.bukkit.ChatColor;

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
