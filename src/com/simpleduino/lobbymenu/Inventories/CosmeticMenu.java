package com.simpleduino.lobbymenu.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Simple-Duino on 24/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class CosmeticMenu {

    private Inventory inv = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "Cosmétiques");

    public CosmeticMenu(Player p)
    {
        ItemStack particleMenu = new ItemStack(Material.REDSTONE, 1);
        ItemMeta particleMeta = particleMenu.getItemMeta();
        particleMeta.setDisplayName(ChatColor.YELLOW + "Particules");
        particleMenu.setItemMeta(particleMeta);
        this.inv.setItem(3, particleMenu);

        ItemStack gadgetsMenu = new ItemStack(Material.SNOW_BALL, 1);
        ItemMeta gadgetsMeta = gadgetsMenu.getItemMeta();
        gadgetsMeta.setDisplayName(ChatColor.YELLOW + "Gadgets");
        gadgetsMenu.setItemMeta(gadgetsMeta);
        this.inv.setItem(5, gadgetsMenu);
    }

    public Inventory getInventory()
    {
        return this.inv;
    }

}
