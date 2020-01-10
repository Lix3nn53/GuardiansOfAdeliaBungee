package io.github.lix3nn53.guardiansofadelia.bungee;

import io.github.lix3nn53.guardiansofadelia.bungee.commands.CommandChangeServer;
import io.github.lix3nn53.guardiansofadelia.bungee.commands.CommandDeveloperTest;
import io.github.lix3nn53.guardiansofadelia.bungee.commands.CommandLobby;
import io.github.lix3nn53.guardiansofadelia.bungee.socket.MySocketServer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class GuardiansOfAdeliaBungee extends Plugin {

    public static ChannelListener channelListener;
    private static MySocketServer mySocketServer;

    private static GuardiansOfAdeliaBungee instance;

    public static GuardiansOfAdeliaBungee getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Yay! It loads!");

        ConfigManager.init();
        ConfigManager.createConfigALL();
        ConfigManager.loadConfigALL();

        getProxy().getPluginManager().registerCommand(this, new CommandChangeServer());
        getProxy().getPluginManager().registerCommand(this, new CommandDeveloperTest());
        getProxy().getPluginManager().registerCommand(this, new CommandLobby());

        ProxyServer.getInstance().getPluginManager().registerListener(this, channelListener = new ChannelListener());
        ProxyServer.getInstance().registerChannel("BungeeCord");
    }

    @Override
    public void onDisable() {
        ConfigManager.writeConfigALL();
        mySocketServer.stop();
    }

    public static void startSocketServer(MySocketServer socketServer) {
        mySocketServer = socketServer;

        instance.getProxy().getScheduler().runAsync(GuardiansOfAdeliaBungee.getInstance(), () -> {
            mySocketServer.start();
        });
    }
}

