package com.simpleduino.lobbymenu.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.simpleduino.lobbymenu.ServersListing;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Simple-Duino on 07/07/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class MainMenu {

    private Inventory inv = Bukkit.createInventory(null, 54, ChatColor.YELLOW + "Menu principal");
    private ArrayList<Integer> placedItems = new ArrayList<>();
    private ServersListing serverChecker = new ServersListing();

    public MainMenu()
    {
        ItemStack creative_item = new ItemStack(Material.DIAMOND_BLOCK, 1);
        ItemMeta creative_meta = creative_item.getItemMeta();
        creative_meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.LIGHT_PURPLE+"Créatif");
        ArrayList lore = new ArrayList();
        if(serverChecker.getOnlineServers("Créatif").size()>0)
        {
            lore.add(ChatColor.RESET.toString()+ChatColor.GREEN+"En ligne");
        }
        else
        {
            lore.add(ChatColor.RESET.toString()+ChatColor.DARK_RED+"Hors ligne");
        }
        creative_meta.setLore(lore);
        creative_item.setItemMeta(creative_meta);
        this.inv.setItem(19, creative_item);
        placedItems.add(19);

        ItemStack pvp_fac_item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta pvp_fac_meta = pvp_fac_item.getItemMeta();
        pvp_fac_meta.setDisplayName("PVP/Faction");
        ArrayList lore2 = new ArrayList();
        if(serverChecker.getOnlineServers("PVP/Faction").size()>0)
        {
            lore2.add(ChatColor.RESET.toString()+ChatColor.GREEN+"En ligne");
        }
        else
        {
            lore2.add(ChatColor.RESET.toString()+ChatColor.DARK_RED+"Hors ligne");
        }
        pvp_fac_meta.setLore(lore2);
        pvp_fac_item.setItemMeta(pvp_fac_meta);
        this.inv.setItem(21, pvp_fac_item);
        placedItems.add(21);

        ItemStack pvp_box_item = new ItemStack(Material.WOOD_SWORD, 1);
        ItemMeta pvp_box_meta = pvp_box_item.getItemMeta();
        pvp_box_meta.setDisplayName("PVP-Box");
        ArrayList lore3 = new ArrayList();
        if(serverChecker.getOnlineServers("PVP-Box").size()>0)
        {
            lore3.add(ChatColor.RESET.toString()+ChatColor.GREEN+"En ligne");
        }
        else
        {
            lore3.add(ChatColor.RESET.toString()+ChatColor.DARK_RED+"Hors ligne");
        }
        pvp_box_meta.setLore(lore3);
        pvp_box_item.setItemMeta(pvp_box_meta);
        this.inv.setItem(23, pvp_box_item);
        placedItems.add(23);

        ItemStack freebuild_item = new ItemStack(Material.GRASS, 1);
        ItemMeta freebuild_meta = freebuild_item.getItemMeta();
        freebuild_meta.setDisplayName("Freebuild");
        ArrayList lore4 = new ArrayList();
        if(serverChecker.getOnlineServers("Freebuild").size()>0)
        {
            lore4.add(ChatColor.RESET.toString()+ChatColor.GREEN+"En ligne");
        }
        else
        {
            lore4.add(ChatColor.RESET.toString()+ChatColor.DARK_RED+"Hors ligne");
        }
        freebuild_meta.setLore(lore4);
        freebuild_item.setItemMeta(freebuild_meta);
        this.inv.setItem(25, freebuild_item);
        placedItems.add(25);

        ItemStack hoodoo_item = new ItemStack(Material.STAINED_CLAY, 1);
        ItemMeta hoodoo_meta = hoodoo_item.getItemMeta();
        hoodoo_meta.setDisplayName("Hoodoo");
        ArrayList lore5 = new ArrayList();
        if(serverChecker.getOnlineServers("Hoodoo").size()>0)
        {
            lore5.add(ChatColor.RESET.toString()+ChatColor.GREEN+"En ligne");
        }
        else
        {
            lore5.add(ChatColor.RESET.toString()+ChatColor.DARK_RED+"Hors ligne");
        }
        hoodoo_meta.setLore(lore5);
        hoodoo_item.setItemMeta(hoodoo_meta);
        this.inv.setItem(38, hoodoo_item);
        placedItems.add(38);

        ItemStack tower_item = new ItemStack(Material.NETHER_BRICK_STAIRS, 1);
        ItemMeta tower_meta = tower_item.getItemMeta();
        tower_meta.setDisplayName("Tower");
        ArrayList lore6 = new ArrayList();
        if(serverChecker.getOnlineServers("Tower").size()>0)
        {
            lore6.add(ChatColor.RESET.toString()+ChatColor.GREEN+"En ligne");
        }
        else
        {
            lore6.add(ChatColor.RESET.toString()+ChatColor.DARK_RED+"Hors ligne");
        }
        tower_meta.setLore(lore6);
        tower_item.setItemMeta(tower_meta);
        this.inv.setItem(40, tower_item);
        placedItems.add(40);

        ItemStack deathorb_item = new ItemStack(Material.EXP_BOTTLE, 1);
        ItemMeta deathorb_meta = deathorb_item.getItemMeta();
        deathorb_meta.setDisplayName("Deathorb");
        ArrayList lore7 = new ArrayList();
        if(serverChecker.getOnlineServers("Deathorb").size()>0)
        {
            lore7.add(ChatColor.RESET.toString()+ChatColor.GREEN+"En ligne");
        }
        else
        {
            lore7.add(ChatColor.RESET.toString()+ChatColor.DARK_RED+"Hors ligne");
        }
        deathorb_meta.setLore(lore7);
        deathorb_item.setItemMeta(deathorb_meta);
        this.inv.setItem(42, deathorb_item);
        placedItems.add(42);

        for(int i=0;i<=53;i++)
        {
            if(!placedItems.contains(i))
            {
                Random r = new Random();
                int Low = 0;
                int High = 15;
                int durability = r.nextInt(High-Low) + Low;
                ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE, 1);
                pane.setDurability((short)durability);
                this.inv.setItem(i, pane);
            }
        }
    }

    public Inventory getInventory()
    {
        return this.inv;
    }

}
