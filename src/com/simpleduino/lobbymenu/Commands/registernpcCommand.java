package com.simpleduino.lobbymenu.Commands;

import com.simpleduino.lobbymenu.Entities.CraftEntities.CustomCraftVillager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.io.File;
import java.io.IOException;

/**
 * Created by Simple-Duino on 28/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class registernpcCommand implements CommandExecutor {

    private File npcList = new File("plugins/LobbyMenu/npcList.yml");
    private YamlConfiguration npcCfg = YamlConfiguration.loadConfiguration(npcList);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            Player p = ((Player) sender);
            if(p.hasPermission("lobbymenu.admin.registernpc")) {
                if (args.length >= 2) {
                    String customName = args[0];
                    String profession = args[1];
                    Location l = p.getLocation();
                    npcCfg.set(customName + ".world", l.getWorld().getName());
                    npcCfg.set(customName + ".x", l.getX());
                    npcCfg.set(customName + ".y", l.getY());
                    npcCfg.set(customName + ".z", l.getZ());
                    npcCfg.set(customName + ".yaw", l.getY());
                    npcCfg.set(customName + ".pitch", l.getPitch());
                    npcCfg.set(customName + ".profession", profession);
                    try {
                        npcCfg.save(npcList);
                        CustomCraftVillager.spawn(l, customName.replace("&", "§"), Villager.Profession.valueOf(profession.toUpperCase()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    p.sendMessage(ChatColor.RED + "Erreur: Syntaxe incorrecte, utilisez /registernpc <custom_name> <blacksmith|butcher|librarian|farmer|priest>");
                }
            }
            else
            {
                p.sendMessage(ChatColor.RED + "Erreur: Vous n'avez pas la permission d'exécuter cette commande");
            }
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Seul un joueur peut exécuter cette commande");
        }
        return false;
    }
}
