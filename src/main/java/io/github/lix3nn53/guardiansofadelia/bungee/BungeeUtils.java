package io.github.lix3nn53.guardiansofadelia.bungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeUtils {

    public static void transportToServer(ProxiedPlayer player, String serverName) {
        if (player.getServer().getInfo().getName().equalsIgnoreCase(serverName)) {
            player.sendMessage(new ComponentBuilder("You are already connected to the " + serverName).color(ChatColor.RED).create());
            return;
        }

        ServerInfo target = ProxyServer.getInstance().getServerInfo(serverName);
        player.connect(target);
    }
}
