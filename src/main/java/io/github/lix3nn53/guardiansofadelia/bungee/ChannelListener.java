package io.github.lix3nn53.guardiansofadelia.bungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChannelListener  implements Listener {

    @EventHandler
    public void onPluginMessage(PluginMessageEvent e) {
        if (e.getTag().equalsIgnoreCase("BungeeCord")) {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));
            try {
                String subchannel = in.readUTF(); // subchannel we delivered
                if(subchannel.equals("webPurchaseResponseFromRpg")){

                    ServerInfo server = ProxyServer.getInstance().getPlayer(e.getReceiver().toString()).getServer().getInfo(); //source server of request
                    String input = in.readUTF(); // the inputstring

                    sendToBukkit(subchannel, Collections.singletonList(input), server);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    public void sendToBukkit(String subchannel, List<String> messages, ServerInfo server) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF(subchannel);
            for (String message : messages) {
                out.writeUTF(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.sendData("BungeeCord", stream.toByteArray(), false);
    }
}