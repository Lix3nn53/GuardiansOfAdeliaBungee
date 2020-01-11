package io.github.lix3nn53.guardiansofadelia.bungee.socket;

import io.github.lix3nn53.guardiansofadelia.bungee.BungeeUtils;
import io.github.lix3nn53.guardiansofadelia.bungee.GuardiansOfAdeliaBungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.*;

public class RequestHandler {

    private final static String mainServerForRequests = "rpg1";
    private final static HashMap<Integer, WebProduct> productIdToWebProduct = new HashMap<>();

    static {
        //list of ranks
        productIdToWebProduct.put(1, new WebProduct(ChatColor.GREEN + "Hero Rank"));
        productIdToWebProduct.put(2, new WebProduct(ChatColor.GOLD + "Legend Rank"));
        productIdToWebProduct.put(3, new WebProduct(ChatColor.LIGHT_PURPLE + "Titan Rank"));

        //List of items
        productIdToWebProduct.put(4, new WebProduct(ChatColor.LIGHT_PURPLE + "Weapon/Shield Skin Scroll x1"));
        productIdToWebProduct.put(5, new WebProduct(ChatColor.LIGHT_PURPLE + "Weapon/Shield Skin Scroll x2"));
        productIdToWebProduct.put(6, new WebProduct(ChatColor.LIGHT_PURPLE + "Weapon/Shield Skin Scroll x5"));

        productIdToWebProduct.put(7, new WebProduct(ChatColor.YELLOW + "Angel Wings(Helmet Skin)"));
        productIdToWebProduct.put(8, new WebProduct(ChatColor.DARK_PURPLE + "Demon Wings(Helmet Skin)"));
        productIdToWebProduct.put(9, new WebProduct(ChatColor.LIGHT_PURPLE + "Dark Dragon Wings(Helmet Skin)"));
        productIdToWebProduct.put(10, new WebProduct(ChatColor.AQUA + "White Dragon Wings(Helmet Skin)"));
        productIdToWebProduct.put(11, new WebProduct(ChatColor.GOLD + "Golden Crown(Helmet Skin)"));

        productIdToWebProduct.put(12, new WebProduct(ChatColor.YELLOW + "Bee(Pet Skin)"));
        productIdToWebProduct.put(13, new WebProduct(ChatColor.GOLD + "Red Fox(Pet Skin)"));
        productIdToWebProduct.put(14, new WebProduct(ChatColor.AQUA + "Snow Fox(Pet Skin)"));
        productIdToWebProduct.put(15, new WebProduct(ChatColor.LIGHT_PURPLE + "Ice Cream(Pet Skin)"));
        productIdToWebProduct.put(16, new WebProduct(ChatColor.DARK_AQUA + "Vex(Pet Skin)"));
        productIdToWebProduct.put(17, new WebProduct(ChatColor.RED + "Baby Dragon(Pet Skin)"));

        productIdToWebProduct.put(18, new WebProduct(ChatColor.GOLD + "Skin Chest x1"));
        productIdToWebProduct.put(19, new WebProduct(ChatColor.GOLD + "Skin Chest x2"));
        productIdToWebProduct.put(20, new WebProduct(ChatColor.GOLD + "Skin Chest x5"));

        productIdToWebProduct.put(21, new WebProduct(ChatColor.LIGHT_PURPLE + "Experience Boost x1"));
        productIdToWebProduct.put(22, new WebProduct(ChatColor.LIGHT_PURPLE + "Experience Boost x2"));
        productIdToWebProduct.put(23, new WebProduct(ChatColor.LIGHT_PURPLE + "Experience Boost x5"));
        productIdToWebProduct.put(24, new WebProduct(ChatColor.YELLOW + "Loot Boost x1"));
        productIdToWebProduct.put(25, new WebProduct(ChatColor.YELLOW + "Loot Boost x2"));
        productIdToWebProduct.put(26, new WebProduct(ChatColor.YELLOW + "Loot Boost x5"));
        productIdToWebProduct.put(27, new WebProduct(ChatColor.AQUA + "Enchant Boost x1"));
        productIdToWebProduct.put(28, new WebProduct(ChatColor.AQUA + "Enchant Boost x2"));
        productIdToWebProduct.put(29, new WebProduct(ChatColor.AQUA + "Enchant Boost x5"));
        productIdToWebProduct.put(30, new WebProduct(ChatColor.GREEN + "Gather Boost x1"));
        productIdToWebProduct.put(31, new WebProduct(ChatColor.GREEN + "Gather Boost x2"));
        productIdToWebProduct.put(32, new WebProduct(ChatColor.GREEN + "Gather Boost x5"));

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

        WebResponse webResponse = (WebResponse) GuardiansOfAdeliaBungee.channelListener.sendThenGet("webPurchase", messages, serverInfo);

        if (webResponse == null) {
            return new WebResponse(false, "There must be at least 1 player online on GameServer-1 OR you must be online on any GameServer.", minecraftUsername, productId);
        }

        if (webResponse.isSuccess()) {
            WebProduct webProduct = productIdToWebProduct.get(productId);
            BungeeUtils.broadcastMessage(ChatColor.GOLD + "Thanks for your support! " + ChatColor.WHITE + webPurchase.getMinecraftUsername() + ChatColor.GRAY + " bought "
                    + webProduct.getProductName() + ChatColor.GRAY + " from web-store!");
        }

        return webResponse;
    }
}
