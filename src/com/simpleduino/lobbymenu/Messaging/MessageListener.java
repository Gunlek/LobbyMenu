package com.simpleduino.lobbymenu.Messaging;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.simpleduino.lobbymenu.ServersListing;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

/**
 * Created by Simple-Duino on 07/07/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class MessageListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {

        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("PlayerCount")) {
            String server = in.readUTF(); // Name of server, as given in the arguments
            int playercount = in.readInt();

            if(ServersListing.PlayerCountOnServer.containsKey(server))
            {
                ServersListing.PlayerCountOnServer.remove(server);
            }

            ServersListing.PlayerCountOnServer.put(server, playercount);
        }

    }

}
