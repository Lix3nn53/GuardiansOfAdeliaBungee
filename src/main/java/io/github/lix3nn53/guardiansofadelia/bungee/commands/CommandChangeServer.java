package io.github.lix3nn53.guardiansofadelia.bungee.commands;

import io.github.lix3nn53.guardiansofadelia.bungee.BungeeUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandChangeServer extends Command {

    public CommandChangeServer() {
        super("changeserver");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (strings.length < 1) return;

        if (!(commandSender instanceof ProxiedPlayer)) {
            commandSender.sendMessage(new ComponentBuilder("This command can only be run by a player!").color(ChatColor.RED).create());
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        String serverName = strings[0];
        BungeeUtils.transportToServer(player, serverName);
    }
}
