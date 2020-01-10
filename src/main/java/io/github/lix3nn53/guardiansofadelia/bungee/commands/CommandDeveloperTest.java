package io.github.lix3nn53.guardiansofadelia.bungee.commands;

import io.github.lix3nn53.guardiansofadelia.bungee.BungeeUtils;
import io.github.lix3nn53.guardiansofadelia.bungee.GuardiansOfAdeliaBungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandDeveloperTest extends Command {

    public CommandDeveloperTest() {
        super("bungeetest");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        List<String> messages = new ArrayList<>();
        messages.add("BUNGEE-TEST");
        GuardiansOfAdeliaBungee.channelListener.sendToBukkit("BungeeCord", messages, ProxyServer.getInstance().getServerInfo("rpg1"));
    }
}
