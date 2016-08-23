package com.simpleduino.lobbymenu.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

/**
 * Created by Simple-Duino on 07/07/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class ProfileMenu {

    private Inventory inv = Bukkit.createInventory(null, 54, ChatColor.YELLOW + "Profil du joueur");

    public ProfileMenu()
    {

    }

    public Inventory getInventory()
    {
        return this.inv;
    }

}
