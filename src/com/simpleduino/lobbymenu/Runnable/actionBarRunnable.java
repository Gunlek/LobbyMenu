package com.simpleduino.lobbymenu.Runnable;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Created by Simple-Duino on 25/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class actionBarRunnable implements Runnable{

    File msgFile = new File("plugins/LobbyMenu/messages.yml");
    YamlConfiguration msgCfg;
    private int messageNumber;
    private int messageSize = 60;
    private int startIndex = -this.messageSize+1;

    public actionBarRunnable()
    {
        if(!msgFile.exists())
        {
            msgFile.getParentFile().mkdirs();
            try {
                msgFile.createNewFile();
                this.msgCfg = YamlConfiguration.loadConfiguration(msgFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            this.msgCfg = YamlConfiguration.loadConfiguration(msgFile);
        this.messageNumber = msgCfg.getKeys(false).size();
    }

    @Override
    public void run() {
        if(this.messageNumber > 0) {
            String msg = "";

            for(int i = 0;i<this.messageNumber;i++)
            {
                if(i<this.messageNumber-1)
                    msg += msgCfg.get(msgCfg.getKeys(false).toArray()[i]+".message").toString()+" &r- ";
                else
                    msg += msgCfg.get(msgCfg.getKeys(false).toArray()[i]+".message").toString();
            }

            msg = msg.replace("&", "§");
            msg = msg.replace("é", "e");
            msg = msg.replace("è", "e");
            msg = msg.replace("ê", "e");
            msg = msg.replace("ë", "e");
            msg = msg.replace("à", "a");
            msg = msg.replace("â", "a");
            msg = msg.replace("ä", "a");
            msg = msg.replace("î", "i");
            msg = msg.replace("ï", "i");
            msg = msg.replace("ù", "u");
            String displayMsg = msg;

            displayMsg = "";
            for(int i=this.startIndex;i<this.startIndex+this.messageSize;i++)
            {
                try
                {
                    displayMsg+=msg.toCharArray()[i];
                }
                catch(Exception e)
                {
                    displayMsg+=" ";
                }
            }
            this.startIndex++;
            if(this.startIndex>=msg.length())
                this.startIndex = -this.messageSize;

            IChatBaseComponent actionBarComp = IChatBaseComponent.ChatSerializer.a("{\"text\": \""+displayMsg+"\"}");
            PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(actionBarComp, (byte) 2);

            for (Player p : Bukkit.getOnlinePlayers()) {
                CraftPlayer cp = (CraftPlayer) p;
                cp.getHandle().playerConnection.sendPacket(packetPlayOutChat);
            }
        }
    }
}
