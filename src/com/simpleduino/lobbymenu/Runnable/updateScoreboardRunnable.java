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
        if(Bukkit.getOnlinePlayers().size()>0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                Scoreboard sc;
                try {
                    sc = p.getScoreboard();
                } catch (Exception e) {
                    sc = Bukkit.getScoreboardManager().getNewScoreboard();
                }
            }
        }
    }
}
