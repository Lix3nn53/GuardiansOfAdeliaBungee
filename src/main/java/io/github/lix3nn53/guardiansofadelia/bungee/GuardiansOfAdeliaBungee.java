package io.github.lix3nn53.guardiansofadelia.bungee;

import io.github.lix3nn53.guardiansofadelia.bungee.commands.CommandChangeServer;
import io.github.lix3nn53.guardiansofadelia.bungee.commands.CommandDeveloperTest;
import io.github.lix3nn53.guardiansofadelia.bungee.commands.CommandLobby;
import net.md_5.bungee.api.plugin.Plugin;

public class GuardiansOfAdeliaBungee extends Plugin {

    @Override
    public void onEnable() {
        // You should not put an enable message in your plugin.
        // BungeeCord already does so
        getLogger().info("Yay! It loads!");

        getProxy().getPluginManager().registerCommand(this, new CommandChangeServer());
        getProxy().getPluginManager().registerCommand(this, new CommandDeveloperTest());
        getProxy().getPluginManager().registerCommand(this, new CommandLobby());
    }


}

