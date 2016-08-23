package com.simpleduino.lobbymenu.Inventories;

import com.simpleduino.shopAPI.APIObjects.ParticleType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Simple-Duino on 23/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class ParticleMenu {

    private Inventory inv = Bukkit.createInventory(null, 36, ChatColor.YELLOW + "Particules");

    public ParticleMenu(Player p)
    {
        int index = 0;
        for(ParticleType particleType : ParticleType.values())
        {
            ItemStack item = new ItemStack(particleType.getMaterial(), 1);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GREEN + particleType.toString());
            item.setItemMeta(itemMeta);
            this.inv.setItem(index, item);
            index++;
        }

        ItemStack noParticle = new ItemStack(Material.BARRIER, 1);
        ItemMeta noParticleMeta = noParticle.getItemMeta();
        noParticleMeta.setDisplayName(ChatColor.DARK_RED + "Supprimer mes particules");
        noParticle.setItemMeta(noParticleMeta);
        this.inv.setItem(index, noParticle);
    }

    public Inventory getInventory()
    {
        return this.inv;
    }
}
