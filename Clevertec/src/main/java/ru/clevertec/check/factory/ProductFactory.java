package ru.clevertec.check.factory;

import ru.clevertec.check.Product;

public class ProductFactory {
    public static Product createProduct(int id, String description, double price, int quantityInStock, boolean wholesaleProduct){
        return new Product(id, description, price, quantityInStock, wholesaleProduct);
    }
}
