package com.simpleduino.lobbymenu.Listeners.MenuListeners;

import com.simpleduino.lobbymenu.Inventories.ParticleMenu;
import com.simpleduino.lobbymenu.LobbyMenuPlugin;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by Simple-Duino on 24/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class CosmeticMenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        final Player p = (Player)e.getWhoClicked();
        if(ChatColor.stripColor(e.getInventory().getTitle()).equalsIgnoreCase("cosmétiques")) {
            if(e.getCurrentItem()!=null && e.getCurrentItem().hasItemMeta()) {
                switch (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase()) {
                    case "particules":
                        p.closeInventory();
                        Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyMenuPlugin.getPlugin(LobbyMenuPlugin.class), new Runnable() {
                            @Override
                            public void run() {
                                p.openInventory(new ParticleMenu(p).getInventory());
                            }
                        }, 1L);
                        e.setCancelled(true);
                        break;

                    case "gadgets":
                        p.closeInventory();
                        p.sendMessage(ChatColor.RED + "Coming Soon...");
                        e.setCancelled(true);
                        break;
                }
            }
        }
    }

}
