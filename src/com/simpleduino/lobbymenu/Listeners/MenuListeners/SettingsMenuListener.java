package com.simpleduino.lobbymenu.Listeners.MenuListeners;

import com.simpleduino.lobbymenu.Inventories.SettingsMenu;
import com.simpleduino.lobbymenu.LobbyMenuPlugin;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;
import java.io.IOException;

/**
 * Created by Simple-Duino on 13/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class SettingsMenuListener implements Listener {

    @EventHandler
    public void onPlayerInteractWithInventory(InventoryClickEvent e)
    {
        File f = new File("plugins/LobbyMenu/playerSettings.yml");
        YamlConfiguration playerSettings = YamlConfiguration.loadConfiguration(f);
        final Player p = (Player)e.getWhoClicked();
        if(e.getInventory().getTitle()!=null && ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("paramètres")) {
            if (e.getCurrentItem()!=null && !e.getCurrentItem().equals(Material.AIR) && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                switch (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase().split(" ")[0]) {
                    case "chat":
                        switch (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase().split(" ")[1].replace("(", "").replace(")", "")) {
                            case "désactiver":
                                playerSettings.set(e.getWhoClicked().getUniqueId().toString() + ".enable-chat", "false");
                                break;
                            case "activer":
                                playerSettings.set(e.getWhoClicked().getUniqueId().toString() + ".enable-chat", "true");
                                break;
                        }
                        e.setCancelled(true);
                        Bukkit.getScheduler().runTaskLater(LobbyMenuPlugin.getPlugin(LobbyMenuPlugin.class), new Runnable() {

                            @Override
                            public void run() {
                                p.closeInventory();
                                p.openInventory(new SettingsMenu(p).getInventory());
                            }
                        }, 5L);
                        break;

                    case "particules":
                        switch (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase().split(" ")[1].replace("(", "").replace(")", "")) {
                            case "désactiver":
                                playerSettings.set(e.getWhoClicked().getUniqueId().toString() + ".enable-particles", "false");
                                break;
                            case "activer":
                                playerSettings.set(e.getWhoClicked().getUniqueId().toString() + ".enable-particles", "true");
                                break;
                        }
                        e.setCancelled(true);
                        Bukkit.getScheduler().runTaskLater(LobbyMenuPlugin.getPlugin(LobbyMenuPlugin.class), new Runnable() {

                            @Override
                            public void run() {
                                p.closeInventory();
                                p.openInventory(new SettingsMenu(p).getInventory());
                            }
                        }, 5L);
                        break;

                    case "visibilité":
                        switch (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase().replace("voir les autres ", "").split(" ")[1].replace("(", "").replace(")", "")) {
                            case "désactiver":
                                for(Player player : Bukkit.getOnlinePlayers())
                                {
                                    if(!player.hasPermission("lobbymenu.bypass-hiding"))
                                        p.hidePlayer(player);
                                }
                                playerSettings.set(e.getWhoClicked().getUniqueId().toString() + ".player-visibility", "false");
                                break;
                            case "activer":
                                for(Player player : Bukkit.getOnlinePlayers())
                                {
                                    p.showPlayer(player);
                                }
                                playerSettings.set(e.getWhoClicked().getUniqueId().toString() + ".player-visibility", "true");
                                break;
                        }
                        e.setCancelled(true);
                        Bukkit.getScheduler().runTaskLater(LobbyMenuPlugin.getPlugin(LobbyMenuPlugin.class), new Runnable() {

                            @Override
                            public void run() {
                                p.closeInventory();
                                p.openInventory(new SettingsMenu(p).getInventory());
                            }
                        }, 5L);
                        break;
                }
                try {
                    playerSettings.save(f);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static boolean doPlayerHasParticlesEnabled(Player p)
    {
        File f = new File("plugins/LobbyMenu/playerSettings.yml");
        YamlConfiguration playerSettings = YamlConfiguration.loadConfiguration(f);
        return playerSettings.get(p.getUniqueId().toString() + ".enable-particles").toString().equalsIgnoreCase("true");
    }

}
