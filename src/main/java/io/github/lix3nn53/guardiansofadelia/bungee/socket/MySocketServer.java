package io.github.lix3nn53.guardiansofadelia.bungee.socket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

public class MySocketServer {

    private final SocketIOServer server;

    public MySocketServer(String hostname, int port) {
        Configuration config = new Configuration();
        config.setHostname(hostname);
        config.setPort(port);

        this.server = new SocketIOServer(config);

        this.server.addEventListener("purchase", WebPurchase.class, (socketIOClient, webPurchase, ackRequest) -> {
            System.out.println("webPurchase: " + webPurchase.toString());

            WebResponse webResponse = RequestHandler.onPurchase(webPurchase);
            System.out.println("webResponse: " + webResponse.toString());

            socketIOClient.sendEvent("purchaseResult", webResponse);
        });
    }

    public void start() {
        this.server.start();
    }

    public void stop() {
        this.server.stop();
    }
}
