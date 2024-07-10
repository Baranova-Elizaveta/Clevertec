package ru.clevertec.check;

public class OrderThing {
    private final int productId;
    private final int quantity;

    public OrderThing(int productId, int quantity){
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
