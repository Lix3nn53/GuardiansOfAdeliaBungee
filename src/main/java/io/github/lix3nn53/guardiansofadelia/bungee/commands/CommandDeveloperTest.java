package io.github.lix3nn53.guardiansofadelia.bungee.commands;

import io.github.lix3nn53.guardiansofadelia.bungee.BungeeUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandDeveloperTest extends Command {

    public CommandDeveloperTest() {
        super("bungeetest");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        BungeeUtils.sendData("rpg1");
    }
}
