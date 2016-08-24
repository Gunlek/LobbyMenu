package com.simpleduino.lobbymenu.Listeners.MenuListeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.simpleduino.lobbymenu.LobbyMenuPlugin;
import com.simpleduino.lobbymenu.ServersListing;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Simple-Duino on 24/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class MainMenuListener implements Listener {

    ServersListing serverChecker = new ServersListing();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        Player p = (Player)e.getWhoClicked();
        if(ChatColor.stripColor(e.getInventory().getTitle()).equalsIgnoreCase("menu principal"))
        {
            if(e.getCurrentItem()!=null && !e.getCurrentItem().getType().equals(Material.AIR))
            {
                ItemStack currentItem = e.getCurrentItem();
                if(currentItem.hasItemMeta())
                {
                    switch(ChatColor.stripColor(currentItem.getItemMeta().getDisplayName()).toLowerCase())
                    {
                        case "adresse du teamspeak":
                            p.closeInventory();
                            p.sendMessage(ChatColor.AQUA + "================== Teamspeak ====================");
                            p.sendMessage(ChatColor.AQUA + "Adresse teamspeak: "+ChatColor.BOLD+"ts.nativgaming.com");
                            break;

                        case "adresse du site web":
                            p.closeInventory();
                            p.sendMessage(ChatColor.AQUA + "================== Sites web ====================");
                            p.sendMessage(ChatColor.AQUA + "Adresse endlessfight: "+ChatColor.BOLD+"endless-fight.com");
                            p.sendMessage(ChatColor.AQUA + "Adresse nativgaming: "+ChatColor.BOLD+"nativgaming.com");
                            break;
                    }
                    if(currentItem.getType().equals(Material.STAINED_GLASS_PANE))
                        e.setCancelled(true);
                    if(!currentItem.getType().equals(Material.STAINED_GLASS_PANE)
                            && !currentItem.getType().equals(Material.AIR)
                            && !currentItem.getType().equals(Material.SIGN))
                    {
                        String serverType = ChatColor.stripColor(currentItem.getItemMeta().getDisplayName());
                        serverType = serverType.replace("é", "e");
                        serverType = serverType.replace("è", "e");
                        serverType = serverType.replace(" ", "_");
                        serverType = serverType.toLowerCase();
                        e.getWhoClicked().closeInventory();
                        if(serverChecker.getOnlineServers(serverType).size()>0) {
                            ByteArrayDataOutput out = ByteStreams.newDataOutput();

                            out.writeUTF("ConnectOther");
                            out.writeUTF(e.getWhoClicked().getName());
                            out.writeUTF(serverChecker.getOnlineServers(serverType).get(0));

                            Player player = (Player) e.getWhoClicked();
                            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);

                            player.sendPluginMessage(LobbyMenuPlugin.getPlugin(LobbyMenuPlugin.class), "BungeeCord", out.toByteArray());
                        }
                        else
                        {
                            p.sendMessage(ChatColor.RED + "Aucun serveur en ligne pour ce mode de jeu actuellement");
                        }
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
