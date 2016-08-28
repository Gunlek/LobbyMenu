package com.simpleduino.lobbymenu.Commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Simple-Duino on 28/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class npcselectorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            Player p = (Player)sender;
            if(p.hasPermission("lobbymenu.admin.npcselector"))
            {
                ItemStack stickSelector = new ItemStack(Material.STICK, 1);
                ItemMeta stickMeta = stickSelector.getItemMeta();
                stickMeta.setDisplayName(ChatColor.ITALIC + "Sélécteur NPC");
                stickSelector.setItemMeta(stickMeta);
                stickSelector.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

                p.getInventory().addItem(stickSelector);
            }
            else
            {
                p.sendMessage(ChatColor.RED + "Erreur: Vous n'avez pas la permission d'exécuter cette commande");
            }
        }
        else
        {
            sender.sendMessage(org.bukkit.ChatColor.RED + "Seul un joueur peut exécuter cette commande");
        }
        return false;
    }
}
