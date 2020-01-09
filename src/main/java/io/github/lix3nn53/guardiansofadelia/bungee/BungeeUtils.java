package io.github.lix3nn53.guardiansofadelia.bungee;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.*;

public class BungeeUtils {

    private static final Set<String> servers = new HashSet<>();
    private static final Set<String> onlineServers = new HashSet<>();

    static {
        servers.add("lobby");
        servers.add("rpg1");
    }

    public static void sendMessage(ProxiedPlayer player, String message) {
        player.sendMessage(new TextComponent(message));
    }

    public static void transportToServer(ProxiedPlayer player, String serverName) {
        if (player.getServer().getInfo().getName().equalsIgnoreCase(serverName)) {
            player.sendMessage(new ComponentBuilder("You are already connected to the " + serverName).color(ChatColor.RED).create());
            return;
        }

        ServerInfo target = ProxyServer.getInstance().getServerInfo(serverName);
        player.connect(target);
    }

    public static Collection<ProxiedPlayer> getOnlinePlayers() {
        return ProxyServer.getInstance().getPlayers();
    }

    public static Collection<ProxiedPlayer> getOnlinePlayers(String serverName) {
        return ProxyServer.getInstance().getServerInfo(serverName).getPlayers();
    }

    public static void broadcast(String message) {
        ProxyServer.getInstance().broadcast(new TextComponent(message));
    }

    public static ProxiedPlayer isPlayerOnline() {
        return ProxyServer.getInstance().getPlayer("");
    }

    public static void sendData(String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("webPurchase");
        out.writeUTF("Argument");

        ProxyServer.getInstance().getServerInfo(serverName).sendData("BungeeCord", out.toByteArray(), false);
    }

    public static void updateOnlineServers() {
        onlineServers.clear();

        for (String serverName : servers) {
            ProxyServer.getInstance().getServerInfo(serverName).ping((result, error) -> {
                if (error == null) onlineServers.add(serverName);
            });
        }
    }
}
