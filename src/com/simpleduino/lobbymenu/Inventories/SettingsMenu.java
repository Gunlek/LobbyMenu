package com.simpleduino.lobbymenu.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;

/**
 * Created by Simple-Duino on 07/07/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class SettingsMenu {

    private Inventory inv = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "Paramètres");
    private File f = new File("plugins/LobbyMenu/playerSettings.yml");

    public SettingsMenu(Player p)
    {
        YamlConfiguration playerSettings = YamlConfiguration.loadConfiguration(f);
        ItemStack mapItem = new ItemStack(Material.EMPTY_MAP, 1);
        if(playerSettings.get(p.getUniqueId().toString()+".enable-chat").toString().equalsIgnoreCase("true"))
            mapItem.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta mapItemMeta = mapItem.getItemMeta();
        if(playerSettings.get(p.getUniqueId().toString()+".enable-chat").toString().equalsIgnoreCase("true"))
            mapItemMeta.setDisplayName(ChatColor.GREEN + "Chat (désactiver)");
        else
            mapItemMeta.setDisplayName(ChatColor.DARK_RED + "Chat (activer)");
        mapItem.setItemMeta(mapItemMeta);
        inv.setItem(2, mapItem);

        ItemStack ghastItem = new ItemStack(Material.GHAST_TEAR, 1);
        if(playerSettings.get(p.getUniqueId().toString()+".enable-particles").toString().equalsIgnoreCase("true"))
            ghastItem.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta ghastItemMeta = ghastItem.getItemMeta();
        if(playerSettings.get(p.getUniqueId().toString()+".enable-particles").toString().equalsIgnoreCase("true"))
            ghastItemMeta.setDisplayName(ChatColor.GREEN + "Particules (désactiver)");
        else
            ghastItemMeta.setDisplayName(ChatColor.DARK_RED + "Particules (activer)");
        ghastItem.setItemMeta(ghastItemMeta);
        inv.setItem(4, ghastItem);

        ItemStack eyeItem = new ItemStack(Material.EYE_OF_ENDER, 1);
        if(playerSettings.get(p.getUniqueId().toString()+".player-visibility").toString().equalsIgnoreCase("true"))
            eyeItem.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta eyeItemMeta = eyeItem.getItemMeta();
        if(playerSettings.get(p.getUniqueId().toString()+".player-visibility").toString().equalsIgnoreCase("true"))
            eyeItemMeta.setDisplayName(ChatColor.GREEN + "Visibilité (désactiver)");
        else
            eyeItemMeta.setDisplayName(ChatColor.DARK_RED + "Visibilité (activer)");
        eyeItem.setItemMeta(eyeItemMeta);
        inv.setItem(6, eyeItem);
    }

    public Inventory getInventory()
    {
        return this.inv;
    }

}
