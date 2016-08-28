package com.simpleduino.lobbymenu.Listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.simpleduino.lobbymenu.LobbyMenuPlugin;
import com.simpleduino.lobbymenu.ServersListing;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Simple-Duino on 26/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class EntityListener implements Listener {

    public static HashMap<Player, Villager> selectedNPC = new HashMap<>();

    @EventHandler
    public void onEntityInteract(PlayerInteractAtEntityEvent e)
    {
        e.setCancelled(true);
        if(e.getRightClicked().getType().equals(EntityType.VILLAGER))
        {
            Villager villager = (Villager)e.getRightClicked();
            boolean menu = false;
            ArmorStand armorStand = null;
            for(Entity entity : villager.getEyeLocation().getWorld().getNearbyEntities(villager.getEyeLocation(), 1.0, 1.0, 1.0))
            {
                if(entity.getType().equals(EntityType.ARMOR_STAND))
                {
                    menu = true;
                    armorStand = (ArmorStand)entity;
                    break;
                }
            }
            if(e.getPlayer().getInventory().getItemInHand().getType().equals(Material.STICK)
                    && e.getPlayer().getInventory().getItemInHand().getEnchantments().containsKey(Enchantment.DURABILITY)
                    && ChatColor.stripColor(e.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName()).equalsIgnoreCase("Sélécteur npc"))
            {
                if(menu)
                {
                    if(!selectedNPC.containsKey(e.getPlayer()))
                        selectedNPC.put(e.getPlayer(), villager);
                    else
                    {
                        selectedNPC.remove(e.getPlayer());
                        selectedNPC.put(e.getPlayer(), villager);
                    }

                    final Player p1 = e.getPlayer();
                    Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyMenuPlugin.getPlugin(LobbyMenuPlugin.class), new BukkitRunnable() {
                        @Override
                        public void run() {
                            p1.closeInventory();
                        }
                    }, 1L);
                    e.getPlayer().sendMessage(ChatColor.GREEN + "Vous avez sélectionné le villageois \""+armorStand.getCustomName()+"\"");
                    e.setCancelled(true);
                }
            }
            else {
                if (menu) {
                    String customName = armorStand.getCustomName();
                    if (customName != null && !customName.equalsIgnoreCase("")) {
                        ArrayList<String> serverList = new ServersListing().getOnlineServers(customName.toLowerCase());
                        if (serverList.size() > 0) {
                            int inventorySize = (int) (Math.floor(serverList.size() % 9) * 9);
                            final Inventory inv;
                            if (inventorySize > 54)
                                inv = Bukkit.createInventory(null, 54, ChatColor.BLUE + customName);
                            else
                                inv = Bukkit.createInventory(null, inventorySize, customName);
                            int index = 0;
                            Wool greenWool = new Wool(DyeColor.GREEN);
                            ItemStack woolStack = greenWool.toItemStack(1);
                            ItemMeta woolMeta = woolStack.getItemMeta();
                            for (String server : serverList) {
                                if (index >= inventorySize)
                                    break;
                                woolMeta.setDisplayName(ChatColor.GREEN + "Rejoindre " + server);
                                woolStack.setItemMeta(woolMeta);
                                inv.setItem(index, woolStack);
                                index++;
                            }
                            final Player p = e.getPlayer();
                            e.getPlayer().closeInventory();
                            Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyMenuPlugin.getPlugin(LobbyMenuPlugin.class), new Runnable() {
                                @Override
                                public void run() {
                                    p.openInventory(inv);
                                }
                            }, 1L);
                        } else {
                            final Player p = e.getPlayer();
                            final Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BLUE + customName);
                            Wool redWool = new Wool(DyeColor.RED);
                            ItemStack redStack = redWool.toItemStack(1);
                            ItemMeta redMeta = redStack.getItemMeta();
                            redMeta.setDisplayName(ChatColor.RED + "Aucun serveur en ligne");
                            redStack.setItemMeta(redMeta);
                            for (int i = 0; i < 9; i++) {
                                inv.setItem(i, redStack);
                            }
                            e.getPlayer().closeInventory();
                            Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyMenuPlugin.getPlugin(LobbyMenuPlugin.class), new Runnable() {
                                @Override
                                public void run() {
                                    p.openInventory(inv);
                                }
                            }, 1L);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent e)
    {
        Entity entity = e.getEntity();
        if(entity instanceof Villager)
        {
            Villager villager = (Villager)entity;
            for(Entity entity1 : villager.getEyeLocation().getWorld().getNearbyEntities(villager.getEyeLocation(), 1.0, 1.0, 1.0))
            {
                if(entity1 instanceof ArmorStand)
                    e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        e.getWhoClicked().sendMessage(e.getCurrentItem().getType().toString());
        if(e.getCurrentItem().getType().equals(Material.WOOL))
        {
            String itemName = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase();
            if(itemName.startsWith("rejoindre "))
            {
                String serverName = itemName.replace("rejoindre ", "");
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();

                out.writeUTF("ConnectOther");
                out.writeUTF(e.getWhoClicked().getName());
                out.writeUTF(serverName);

                Player player = (Player) e.getWhoClicked();
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);

                player.sendPluginMessage(LobbyMenuPlugin.getPlugin(LobbyMenuPlugin.class), "BungeeCord", out.toByteArray());
            }
            else if(itemName.startsWith("aucun serveur"))
            {
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();
            }
        }
    }

}
