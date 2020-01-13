package io.github.lix3nn53.guardiansofadelia.bungee;

import io.github.lix3nn53.guardiansofadelia.bungee.socket.WebResponse;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.*;
import java.util.*;

public class ChannelListener  implements Listener {

    private static HashMap<ServerInfo, Object> responses = new HashMap<>();

    @EventHandler
    public void onPluginMessage(PluginMessageEvent e) {
        if (e.getTag().equalsIgnoreCase("BungeeCord")) {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));
            try {
                String subchannel = in.readUTF(); // subchannel we delivered

                if (subchannel.equalsIgnoreCase("webPurchaseResponse")) {
                    boolean success = (in.readUTF()).equals("true");
                    String message = in.readUTF();
                    String minecraftUsername = in.readUTF();
                    int productId = Integer.parseInt(in.readUTF());

                    WebResponse webResponse = new WebResponse(success, message, minecraftUsername, productId);

                    ServerInfo server = ProxyServer.getInstance().getPlayer(e.getReceiver().toString()).getServer().getInfo(); //source server of request
                    responses.put(server, webResponse);
                } else if (subchannel.equalsIgnoreCase("premiumBoostActivate")) {
                    //activate boost in all servers
                    String boostString = in.readUTF();

                    List<String> args = new ArrayList<>();
                    args.add(boostString);

                    Map<String, ServerInfo> servers = ProxyServer.getInstance().getServers();

                    for (String serverName : servers.keySet()) {
                        if (!serverName.contains("rpg")) continue;

                        sendToBukkit(subchannel, args, servers.get(serverName));
                    }

                    BungeeUtils.broadcastMessage(ChatColor.AQUA + "Activated boost: " + boostString + " across all servers for 40 minutes.");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    public synchronized Object sendThenGet(String subchannel, List<String> args, ServerInfo server) {  // here you can add parameters (e.g. String table, String column, ...)
        sendToBukkit(subchannel, args, server);

        int i = 0;
        while (!responses.containsKey(server)) {
            try {
                wait(100L);
            } catch (InterruptedException e) {
                return null;
            }

            if (i > 30) break;
            i++;
        }

        if (responses.containsKey(server)) {
            Object o = responses.get(server);
            responses.remove(server);
            return o;
        }

        return null;
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