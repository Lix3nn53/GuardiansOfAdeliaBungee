package io.github.lix3nn53.guardiansofadelia.bungee.socket;

public class WebPurchase {

    private int productId;
    private int payment;
    private String minecraftUuid;
    private String password;

    public WebPurchase() {
    }

    public WebPurchase(int productId, int payment, String minecraftUuid, String password) {
        this.productId = productId;
        this.payment = payment;
        this.minecraftUuid = minecraftUuid;
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

    public String getMinecraftUuid() {
        return minecraftUuid;
    }

    public void setMinecraftUuid(String minecraftUuid) {
        this.minecraftUuid = minecraftUuid;
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
        sb.append(", minecraftUuid='").append(minecraftUuid).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
