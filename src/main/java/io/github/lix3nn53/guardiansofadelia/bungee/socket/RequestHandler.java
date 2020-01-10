package io.github.lix3nn53.guardiansofadelia.bungee.socket;

import io.github.lix3nn53.guardiansofadelia.bungee.GuardiansOfAdeliaBungee;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.*;

public class RequestHandler {

    private final static String mainServerForRequests = "rpg1";
    private final static HashMap<Integer, WebProduct> productIdToWebProduct = new HashMap<>();

    static {
        //list of ranks
        productIdToWebProduct.put(1, new WebProduct("Hero Rank",1));
        productIdToWebProduct.put(2, new WebProduct("Legend Rank", 1));
        productIdToWebProduct.put(3, new WebProduct("Titan Rank", 1));

        //List of items
        productIdToWebProduct.put(4, new WebProduct("Weapon/Shield Skin Scroll x1", 1));
        productIdToWebProduct.put(5, new WebProduct("Weapon/Shield Skin Scroll x2", 1));
        productIdToWebProduct.put(6, new WebProduct("Weapon/Shield Skin Scroll x5", 1));

        productIdToWebProduct.put(7, new WebProduct("Angel Wings(Helmet Skin)", 1));
        productIdToWebProduct.put(8, new WebProduct("Demon Wings(Helmet Skin)", 1));
        productIdToWebProduct.put(9, new WebProduct("Dark Dragon Wings(Helmet Skin)", 1));
        productIdToWebProduct.put(10, new WebProduct("White Dragon Wings(Helmet Skin)", 1));
        productIdToWebProduct.put(11, new WebProduct("Golden Crown(Helmet Skin)", 1));

        productIdToWebProduct.put(12, new WebProduct("Bee(Pet Skin)", 1));
        productIdToWebProduct.put(13, new WebProduct("Red Fox(Pet Skin)", 1));
        productIdToWebProduct.put(14, new WebProduct("Snow Fox(Pet Skin)", 1));
        productIdToWebProduct.put(15, new WebProduct("Ice Cream(Pet Skin)", 1));
        productIdToWebProduct.put(16, new WebProduct("Vex(Pet Skin)", 1));
        productIdToWebProduct.put(17, new WebProduct("Baby Dragon(Pet Skin)", 1));

        productIdToWebProduct.put(18, new WebProduct("Skin Chest x1", 1));
        productIdToWebProduct.put(19, new WebProduct("Skin Chest x2", 1));
        productIdToWebProduct.put(20, new WebProduct("Skin Chest x5", 1));

        productIdToWebProduct.put(21, new WebProduct("Experience Boost", 1));
        productIdToWebProduct.put(22, new WebProduct("Loot Boost", 1));
        productIdToWebProduct.put(23, new WebProduct("Enchant Boost", 1));
        productIdToWebProduct.put(24, new WebProduct("Gather Boost", 1));

    }

    public static WebResponse sendWebPurchaseToRPG(WebPurchase webPurchase) {
        int productId = webPurchase.getProductId();
        int payment = webPurchase.getPayment();
        String minecraftUsername = webPurchase.getMinecraftUsername();

        List<String> messages = new ArrayList<>();
        messages.add(String.valueOf(productId));
        messages.add(String.valueOf(payment));
        messages.add(minecraftUsername);

        ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(mainServerForRequests); //default server

        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(minecraftUsername);
        if (player != null) {
            serverInfo = player.getServer().getInfo(); //the server player is on
        }

        WebResponse response = (WebResponse) GuardiansOfAdeliaBungee.channelListener.sendThenGet("webPurchase", messages, serverInfo);

        if (response == null) {
            return new WebResponse(false, "There must be at least 1 player online on <GameServer 1> OR you must be online on a GameServer.", minecraftUsername, productId);
        }

        return response;
    }
}
