package ru.clevertec.check.reader;

import ru.clevertec.check.Product;
import ru.clevertec.check.factory.ProductFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductReader {
    public static List<Product> readProducts(String filePath) throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                int id = Integer.parseInt(values[0]);
                String description = values[1];
                double price = Double.parseDouble(values[2]);
                int quantityInStock = Integer.parseInt(values[3]);
                boolean wholesaleProduct = Boolean.parseBoolean(values[4]);
                products.add(ProductFactory.createProduct(id, description, price, quantityInStock, wholesaleProduct));
            }
        }
        return products;
    }
}
