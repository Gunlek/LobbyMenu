package com.simpleduino.lobbymenu;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.simpleduino.lobbymenu.LobbyMenuPlugin;
import com.simpleduino.lobbymenu.SQL.LobbySQL;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Simple-Duino on 13/07/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class ServersListing {

    private HashMap<String, ArrayList<String>> serverList = new HashMap<>();
    private File cfgFile = new File("plugins/LobbyMenu/config.yml");
    private YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);
    public static HashMap<String, Integer> PlayerCountOnServer = new HashMap<>();

    public ServersListing()
    {

    }

    public HashMap<String, ArrayList<String>> getServerList()
    {
        return this.serverList;
    }

    public ArrayList<String> getOnlineServers(String serverType)
    {
        ArrayList<String> onlineServers = new ArrayList<>();
        String sType = serverType;
        sType = sType.toLowerCase();
        sType = sType.replace("é", "e");
        sType = sType.replace("è", "e");
        sType = sType.replace("ë", "e");
        sType = sType.replace("ê", "e");
        sType = sType.replace("à", "a");
        sType = sType.replace("ä", "a");
        sType = sType.replace("â", "a");
        sType = sType.replace(" ", "_");
        HashMap<String, ArrayList<String>> serverList = new LobbySQL().getServerList(sType);
        for(String serverName : serverList.keySet())
        {
            String address = serverList.get(serverName).get(0);
            String port = serverList.get(serverName).get(1);

            SocketAddress socketAddress = new InetSocketAddress(address, Integer.parseInt(port));
            Socket socket = new Socket();
            boolean online = true;
            try
            {
                socket.connect(socketAddress, 50);
            }
            catch(SocketTimeoutException e)
            {
                online = false;
            }
            catch (IOException e) {
                online = false;
            }
            finally
            {
                try
                {
                    socket.close();
                    if(online)
                    {
                        onlineServers.add(serverName);
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        return onlineServers;
    }

    public void getPlayersOnServer(String server)
    {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);

        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);

        player.sendPluginMessage(LobbyMenuPlugin.getPlugin(LobbyMenuPlugin.class), "BungeeCord", out.toByteArray());
    }

    public String getMaxPlayerServer(ArrayList<String> servers)
    {
        boolean available = true;
        for(String s : servers)
        {
            getPlayersOnServer(s);
        }

        int maxPlayer = 0;
        for(String s : servers)
        {
            if(PlayerCountOnServer.get(s) > maxPlayer)
            {
                maxPlayer = PlayerCountOnServer.get(s);
            }
        }
        String server = null;
        for(String s : servers)
        {
            if(PlayerCountOnServer.get(s)==maxPlayer)
            {
                server = s;
            }

        }

        return server;
    }

}
