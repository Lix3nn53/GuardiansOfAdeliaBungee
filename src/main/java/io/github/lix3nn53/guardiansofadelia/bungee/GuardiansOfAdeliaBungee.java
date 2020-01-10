package io.github.lix3nn53.guardiansofadelia.bungee;

import io.github.lix3nn53.guardiansofadelia.bungee.commands.CommandChangeServer;
import io.github.lix3nn53.guardiansofadelia.bungee.commands.CommandDeveloperTest;
import io.github.lix3nn53.guardiansofadelia.bungee.commands.CommandLobby;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class GuardiansOfAdeliaBungee extends Plugin {

    public static ChannelListener channelListener;

    private static GuardiansOfAdeliaBungee instance;

    public static GuardiansOfAdeliaBungee getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        // You should not put an enable message in your plugin.
        // BungeeCord already does so
        getLogger().info("Yay! It loads!");

        getProxy().getPluginManager().registerCommand(this, new CommandChangeServer());
        getProxy().getPluginManager().registerCommand(this, new CommandDeveloperTest());
        getProxy().getPluginManager().registerCommand(this, new CommandLobby());

        ProxyServer.getInstance().getPluginManager().registerListener(this, channelListener = new ChannelListener());
        ProxyServer.getInstance().registerChannel("BungeeCord");
    }


}

