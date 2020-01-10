package io.github.lix3nn53.guardiansofadelia.bungee.socket;

public class WebProduct {

    private final String productName;
    private final int cost;

    public WebProduct(String productName, int cost) {
        this.productName = productName;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public String getProductName() {
        return productName;
    }
}
