package io.github.lix3nn53.guardiansofadelia.bungee.commands;

import io.github.lix3nn53.guardiansofadelia.bungee.BungeeUtils;
import io.github.lix3nn53.guardiansofadelia.bungee.GuardiansOfAdeliaBungee;
import io.github.lix3nn53.guardiansofadelia.bungee.socket.RequestHandler;
import io.github.lix3nn53.guardiansofadelia.bungee.socket.WebPurchase;
import io.github.lix3nn53.guardiansofadelia.bungee.socket.WebResponse;
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
        GuardiansOfAdeliaBungee.getInstance().getProxy().getScheduler().runAsync(GuardiansOfAdeliaBungee.getInstance(), () -> {
            WebPurchase webPurchase = new WebPurchase(4, 1, "Lix3nn", "sad12312d121232f131232131f2421");

            System.out.println("webPurchase: " + webPurchase.toString());

            WebResponse webResponse = RequestHandler.sendWebPurchaseToRPG(webPurchase);
            System.out.println("webResponse: " + webResponse.toString());
        });
    }
}
