package com.simpleduino.lobbymenu.Listeners;

import com.simpleduino.lobbymenu.LobbyMenuPlugin;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Created by Simple-Duino on 17/04/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class ParticleListener implements Listener {

    @EventHandler
    public void onPlayerLoot(PlayerPickupItemEvent e)
    {
        if(ChatColor.stripColor(e.getItem().getItemStack().getItemMeta().getDisplayName()).equalsIgnoreCase("Diamond_particle") || ChatColor.stripColor(e.getItem().getItemStack().getItemMeta().getDisplayName()).equalsIgnoreCase("Emerald_particle"))
        {
            final Item item = e.getItem();
            final Player p = e.getPlayer();
            e.setCancelled(true);
            Bukkit.getScheduler().runTaskLater(LobbyMenuPlugin.getPlugin(LobbyMenuPlugin.class), new Runnable()
            {

                @Override
                public void run() {
                    item.remove();
                    p.updateInventory();
                }
            }, 15);
        }
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e)
    {
        e.setCancelled(true);
    }
}
