package com.simpleduino.lobbymenu.Listeners.MenuListeners;

import com.simpleduino.economy.API.EconomicAPI;
import com.simpleduino.economy.API.EconomicEntities.EconomicAccount;
import com.simpleduino.shopAPI.APIObjects.ShopPlayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * Created by Simple-Duino on 23/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class ConfirmationMenuListener implements Listener {

    private EconomicAPI economicAPI = new EconomicAPI();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        if (ChatColor.stripColor(e.getInventory().getTitle()).equalsIgnoreCase("confirmation")) {
            if (e.getCurrentItem().getType() != Material.AIR) {
                if (!economicAPI.hasAccount(p))
                    economicAPI.createAccount(p);
                EconomicAccount economicAccount = economicAPI.getAccount(p);
                if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("confirmer")) {
                    Inventory inv = e.getInventory();
                    if (inv.getItem(4) != null) {
                        p.sendMessage("item");
                        if (inv.getItem(4).hasItemMeta() && inv.getItem(4).getItemMeta().hasLore()) {
                            p.sendMessage("lore && meta");
                            String object = ChatColor.stripColor(inv.getItem(4).getItemMeta().getLore().get(0).replace("Objet: ", ""));
                            String price = ChatColor.stripColor(inv.getItem(4).getItemMeta().getLore().get(1).replace("Coût: ", "").replace(" coins", ""));
                            String objectType = ChatColor.stripColor(object.split(", ")[0]);
                            String objectName = ChatColor.stripColor(object.split(", ")[1]);
                            int integerPrice = Integer.parseInt(price);

                            if (integerPrice <= economicAccount.getCoins()) {
                                p.sendMessage("money");
                                switch (objectType.toLowerCase()) {
                                    case "particule":
                                        new ShopPlayer(p).addParticleToList(objectName.toLowerCase());
                                        economicAccount.setCoins(economicAccount.getCoins() - integerPrice);
                                        p.closeInventory();
                                        p.sendMessage(ChatColor.GREEN + "Vous avez acheté la particule " + objectName);
                                        e.setCancelled(true);
                                        break;
                                }
                            }
                        }
                    }
                } else {
                    p.closeInventory();
                    p.sendMessage(ChatColor.RED + "Achat annulé");
                    e.setCancelled(true);
                }
            }
        }
    }

}
