package com.simpleduino.lobbymenu.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * Created by Simple-Duino on 23/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class ConfirmationMenu {

    private Inventory inv = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "Confirmation");

    public ConfirmationMenu(Player p, String objectname, String objectType, int price)
    {
        ItemStack i1 = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        ItemMeta im1 = i1.getItemMeta();
        im1.setDisplayName(ChatColor.GREEN + "Confirmer");
        i1.setItemMeta(im1);
        i1.setDurability((short) 5);
        ItemStack i2 = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        ItemMeta im2 = i2.getItemMeta();
        im2.setDisplayName(ChatColor.RED + "Refuser");
        i2.setItemMeta(im2);
        i2.setDurability((short) 14);
        ItemStack i3 = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta im3 = i3.getItemMeta();
        im3.setDisplayName(ChatColor.WHITE + objectname);
        ArrayList<String> a = new ArrayList<>();
        a.add(ChatColor.YELLOW + "Objet: " + objectType+", "+objectname);
        a.add(ChatColor.YELLOW + "Coût: " + Integer.toString(price) + " coins");
        im3.setLore(a);
        i3.setItemMeta(im3);
        this.inv.setItem(2, i1);
        this.inv.setItem(6, i2);
        this.inv.setItem(4, i3);
    }

    public Inventory getInventory()
    {
        return this.inv;
    }

}
