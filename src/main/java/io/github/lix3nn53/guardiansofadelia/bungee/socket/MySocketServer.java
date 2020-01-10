package io.github.lix3nn53.guardiansofadelia.bungee.socket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import io.github.lix3nn53.guardiansofadelia.bungee.BungeeUtils;
import io.github.lix3nn53.guardiansofadelia.bungee.GuardiansOfAdeliaBungee;
import net.md_5.bungee.api.ChatColor;

public class MySocketServer {

    private final SocketIOServer server;
    private final String password;

    public MySocketServer(String hostname, int port, String password) {
        Configuration config = new Configuration();
        config.setHostname(hostname);
        config.setPort(port);

        this.server = new SocketIOServer(config);
        this.password = password;

        this.server.addEventListener("purchase", WebPurchase.class, (socketIOClient, webPurchase, ackRequest) ->
                GuardiansOfAdeliaBungee.getInstance().getProxy().getScheduler().runAsync(GuardiansOfAdeliaBungee.getInstance(), () -> {

            System.out.println("webPurchase: " + webPurchase.toString());
            if (webPurchase.getPassword().equals(this.password)) {
                WebResponse webResponse = RequestHandler.sendWebPurchaseToRPG(webPurchase);
                System.out.println("webResponse: " + webResponse.toString());

                socketIOClient.sendEvent("purchaseResult", webResponse);
            }

        }));
    }

    public void start() {
        this.server.start();
    }

    public void stop() {
        this.server.stop();
    }
}
