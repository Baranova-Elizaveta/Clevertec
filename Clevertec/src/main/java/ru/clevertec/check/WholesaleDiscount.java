package ru.clevertec.check;

public class WholesaleDiscount {
    public static double applyDiscount(Product product, int quantity){
        if (product.isWholesaleProduct() && quantity >= 5) {
            return product.getPrice() * 0.9 * quantity;
        }
        return product.getPrice() * quantity;
    }
}
