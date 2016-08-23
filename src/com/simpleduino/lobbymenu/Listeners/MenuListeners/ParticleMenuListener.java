package com.simpleduino.lobbymenu.Listeners.MenuListeners;

import com.simpleduino.economy.API.EconomicAPI;
import com.simpleduino.economy.API.EconomicEntities.EconomicAccount;
import com.simpleduino.lobbymenu.Inventories.ConfirmationMenu;
import com.simpleduino.lobbymenu.Inventories.ParticleMenu;
import com.simpleduino.lobbymenu.LobbyMenuPlugin;
import com.simpleduino.lobbymenu.particules.Particles;
import com.simpleduino.shopAPI.APIObjects.ParticleType;
import com.simpleduino.shopAPI.APIObjects.ShopPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Simple-Duino on 23/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class ParticleMenuListener implements Listener {

    private EconomicAPI economicAPI = new EconomicAPI();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        if(ChatColor.stripColor(e.getInventory().getTitle()).equalsIgnoreCase("particules")) {
            if(e.getCurrentItem().getType()!= Material.AIR) {
                final Player p = (Player) e.getWhoClicked();
                if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Supprimer mes particules")) {
                    Particles.clear(p);
                    p.closeInventory();
                    e.setCancelled(true);
                } else {
                    if (!economicAPI.hasAccount(p))
                        economicAPI.createAccount(p);
                    EconomicAccount economicAccount = economicAPI.getAccount(p);
                    ShopPlayer sp = new ShopPlayer(p);
                    ItemStack currentItem = e.getCurrentItem();
                    String displayName = null;
                    if (currentItem.hasItemMeta() && currentItem.getItemMeta().hasDisplayName())
                        displayName = ChatColor.stripColor(currentItem.getItemMeta().getDisplayName());
                    if (displayName != null) {
                        if (sp.hasParticle(ParticleType.valueOf(displayName.toUpperCase()))) {
                            Particles.clear(p);
                            LobbyMenuPlugin.particles.get(ParticleType.valueOf(displayName.toUpperCase())).add(p);
                            p.closeInventory();
                            e.setCancelled(true);
                        } else if (economicAccount.getCoins() >= ParticleType.valueOf(displayName.toUpperCase()).getPrice()) {
                            p.closeInventory();
                            final String finalDisplayName = displayName;
                            Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyMenuPlugin.getPlugin(LobbyMenuPlugin.class), new Runnable() {
                                @Override
                                public void run() {
                                    p.openInventory(new ConfirmationMenu(p, ParticleType.valueOf(finalDisplayName.toUpperCase()).toString(), "particule", ParticleType.valueOf(finalDisplayName.toUpperCase()).getPrice()).getInventory());
                                }
                            }, 1L);
                        } else {
                            p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
                            p.sendMessage(ChatColor.RED + "Vous ne possédez pas cette particule");
                            p.closeInventory();
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
