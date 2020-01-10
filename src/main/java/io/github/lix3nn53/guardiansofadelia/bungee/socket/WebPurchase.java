package io.github.lix3nn53.guardiansofadelia.bungee.socket;

public class WebPurchase {

    private int productId;
    private int payment;
    private String minecraftUsername;
    private String password;

    public WebPurchase() {
    }

    public WebPurchase(int productId, int payment, String minecraftUsername, String password) {
        this.productId = productId;
        this.payment = payment;
        this.minecraftUsername = minecraftUsername;
        this.password = password;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getMinecraftUsername() {
        return minecraftUsername;
    }

    public void setMinecraftUsername(String minecraftUsername) {
        this.minecraftUsername = minecraftUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WebPurchase{");
        sb.append("productId=").append(productId);
        sb.append(", payment=").append(payment);
        sb.append(", minecraftUsername='").append(minecraftUsername).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
