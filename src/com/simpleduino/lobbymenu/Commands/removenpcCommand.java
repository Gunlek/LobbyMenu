package com.simpleduino.lobbymenu.Commands;

import com.simpleduino.lobbymenu.Listeners.EntityListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by Simple-Duino on 28/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class removenpcCommand implements CommandExecutor {

    private File npcList = new File("plugins/LobbyMenu/npcList.yml");
    private YamlConfiguration npcCfg = YamlConfiguration.loadConfiguration(npcList);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            Player p = (Player)sender;
            if(p.hasPermission("lobbymenu.admin.removenpc"))
            {
                if(EntityListener.selectedNPC.containsKey(p))
                {
                    Villager villager = EntityListener.selectedNPC.get(p);
                    ArmorStand armorStand = null;
                    for(Entity e : villager.getLocation().getWorld().getNearbyEntities(villager.getLocation(), 1, 1, 1))
                    {
                        if(e.getType().equals(EntityType.ARMOR_STAND))
                            armorStand = (ArmorStand)e;
                    }
                    if(armorStand!=null) {
                        String entityName = armorStand.getName();
                        npcCfg.set(entityName+".world", null);
                        npcCfg.set(entityName+".x", null);
                        npcCfg.set(entityName+".y", null);
                        npcCfg.set(entityName+".z", null);
                        npcCfg.set(entityName+".yaw", null);
                        npcCfg.set(entityName+".pitch", null);
                        npcCfg.set(entityName+".profession", null);
                        npcCfg.set(entityName, null);
                        armorStand.remove();
                        villager.remove();
                        try {
                            npcCfg.save(npcList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        EntityListener.selectedNPC.remove(p);
                    }
                    else
                    {
                        p.sendMessage(ChatColor.RED + "Erreur: Le npc séléctionné n'est pas identifié comme un npc téléporteur");
                    }
                }
                else
                {
                    p.sendMessage(ChatColor.RED + "Erreur: Vous devez sélectionner un npc à supprimer");
                }
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
