package io.github.lix3nn53.guardiansofadelia.bungee.socket;

import io.github.lix3nn53.guardiansofadelia.bungee.BungeeUtils;
import io.github.lix3nn53.guardiansofadelia.bungee.GuardiansOfAdeliaBungee;

import java.util.*;

public class RequestHandler {

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

    public static WebResponse onPurchase(WebPurchase webPurchase) {
        String minecraftUsername = webPurchase.getMinecraftUsername();
        int productId = webPurchase.getProductId();
        int payment = webPurchase.getPayment();

        if (!productIdToWebProduct.containsKey(productId)) {
            return new WebResponse(false, "No such product1", minecraftUsername, productId);
        }

        WebProduct webProduct = productIdToWebProduct.get(productId);
        int cost = webProduct.getCost();

        if (cost != payment) {
            return new WebResponse(false, "No such product2", minecraftUsername, productId);
        }

        GuardiansOfAdeliaBungee.getInstance().getLogger().info("Web purchase: " + minecraftUsername + " bought " + webProduct.getProductName() + " for " + payment + " credits!");
        return new WebResponse(true, "Item purchased successfully!", minecraftUsername, productId);
    }

    private static void sendWebPurchase(UUID uuid) {
        String[] args = {"asd", "asdas", "asdasd"};

        BungeeUtils.sendData("rpg1", "webPurchase", args);
    }
}
