package com.simpleduino.lobbymenu.Runnable;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Created by Simple-Duino on 07/07/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class updateScoreboardRunnable implements Runnable {

    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers())
        {
            Scoreboard sc = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective obj = sc.registerNewObjective("endlessfight", "dummy");
            Score player_number = obj.getScore(ChatColor.BLUE+"Joueurs (Lobby): "+ChatColor.RED + Integer.toString(Bukkit.getOnlinePlayers().size()));
            player_number.setScore(-1);
            Score tokens = obj.getScore(ChatColor.GOLD+"Tokens: "+ChatColor.RED+"0");
            tokens.setScore(-2);
            Score coins = obj.getScore(ChatColor.YELLOW+"Coins: "+ChatColor.RED+"0");
            coins.setScore(-3);
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName(ChatColor.DARK_GREEN.toString()+ChatColor.BOLD+"Endless"+ChatColor.WHITE+"Fight");
            p.setScoreboard(sc);
        }
    }
}
