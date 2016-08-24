package com.simpleduino.lobbymenu.Runnable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

/**
 * Created by Simple-Duino on 24/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class updateScoreboardTitle implements Runnable {

    private int index = 0;

    @Override
    public void run() {
        String displayName = "";
        switch(index)
        {
            case 0:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "E";
                break;
            case 1:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "En";
                break;
            case 2:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "End";
                break;
            case 3:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endl";
                break;
            case 4:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endle";
                break;
            case 5:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endles";
                break;
            case 6:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless";
                break;
            case 7:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"f";
                break;
            case 8:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fi";
                break;
            case 9:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fig";
                break;
            case 10:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"figh";
                break;
            case 11:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                break;
            case 12:
                displayName = ChatColor.GOLD.toString() + ChatColor.BOLD + "E" + ChatColor.DARK_GREEN + "ndless"+ChatColor.WHITE+"fight";
                break;
            case 13:
                displayName = ChatColor.GRAY.toString() + ChatColor.BOLD + "E" + ChatColor.GOLD + "n"+ChatColor.DARK_GREEN+"dless"+ChatColor.WHITE+"fight";
                break;
            case 14:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "E" + ChatColor.GRAY + "n"+ChatColor.GOLD+"d"+ChatColor.DARK_GREEN+"less"+ChatColor.WHITE+"fight";
                break;
            case 15:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "En" + ChatColor.GRAY + "d"+ChatColor.GOLD+"l"+ChatColor.DARK_GREEN+"ess"+ChatColor.WHITE+"fight";
                break;
            case 16:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "End" + ChatColor.GRAY + "l"+ChatColor.GOLD+"e"+ChatColor.DARK_GREEN+"ss"+ChatColor.WHITE+"fight";
                break;
            case 17:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endl" + ChatColor.GRAY + "e"+ChatColor.GOLD+"s"+ChatColor.DARK_GREEN+"s"+ChatColor.WHITE+"fight";
                break;
            case 18:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endle" + ChatColor.GRAY + "s"+ChatColor.GOLD+"s"+ChatColor.WHITE+"fight";
                break;
            case 19:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endles" + ChatColor.GRAY + "s"+ChatColor.GOLD+"f"+ChatColor.WHITE+"ight";
                break;
            case 20:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless" + ChatColor.GRAY + "f"+ChatColor.GOLD+"i"+ChatColor.WHITE+"ght";
                break;
            case 21:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"f" + ChatColor.GRAY + "i"+ChatColor.GOLD+"g"+ChatColor.WHITE+"ht";
                break;
            case 22:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fi" + ChatColor.GRAY + "g"+ChatColor.GOLD+"h"+ChatColor.WHITE+"t";
                break;
            case 23:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fig" + ChatColor.GRAY + "h"+ChatColor.GOLD+"t";
                break;
            case 24:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"figh" + ChatColor.GRAY + "t";
                break;
            case 25:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                break;
            case 26:
                displayName = "";
                break;
            case 27:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                break;
            case 28:
                displayName = "";
                break;
            case 29:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                break;
            case 30:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                break;
            case 31:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                break;
            case 32:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                break;
            case 33:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                break;
            case 34:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                break;
            case 35:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                break;
            case 36:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                break;
            case 37:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                break;
            case 38:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                break;
            case 39:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                break;
            case 40:
                displayName = ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Endless"+ChatColor.WHITE+"fight";
                index = -1;
                break;

        }
        for(Player p : Bukkit.getOnlinePlayers())
        {
            p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(displayName);
        }

        index++;
    }
}
