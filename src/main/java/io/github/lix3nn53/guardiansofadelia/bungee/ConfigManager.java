package io.github.lix3nn53.guardiansofadelia.bungee;

import io.github.lix3nn53.guardiansofadelia.bungee.socket.MySocketServer;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigManager {

    private static File DATA_FOLDER;
    private static Configuration socketServer;

    public static void init() {
        if (!GuardiansOfAdeliaBungee.getInstance().getDataFolder().exists()) {
            GuardiansOfAdeliaBungee.getInstance().getDataFolder().mkdirs();
        }
        DATA_FOLDER = GuardiansOfAdeliaBungee.getInstance().getDataFolder();
    }

    public static void createConfigALL() {
        createSocketServer();
    }

    public static void loadConfigALL() {
        loadSocketServer();
    }

    public static void writeConfigALL() {

    }

    private static void createSocketServer() {
        File file = new File(DATA_FOLDER, "socketServer.yml");

        if (!file.exists()) {
            try (InputStream in = GuardiansOfAdeliaBungee.getInstance().getResourceAsStream("socketServer.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadSocketServer() {
        try {
            socketServer = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(DATA_FOLDER, "socketServer.yml"));

            String hostname = socketServer.getString("hostname");
            int port = socketServer.getInt("port");
            String password = socketServer.getString("password");

            MySocketServer mySocketServer = new MySocketServer(hostname, port, password);
            GuardiansOfAdeliaBungee.startSocketServer(mySocketServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
