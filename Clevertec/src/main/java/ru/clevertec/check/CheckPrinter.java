package ru.clevertec.check;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CheckPrinter {
    public static void printToConsole(Check check) throws NotEnoughMoneyException {
        System.out.println(generateCheckString(check));
    }

    public static void printToFile(Check check, Path filePath) throws IOException {
        List<String> lines = generateCheckLines(check);
        Files.write(filePath, lines);
    }

    private static List<String> generateCheckLines(Check check) {
        List<String> lines = new ArrayList<>();
        lines.add("Date: " + java.time.LocalDate.now());
        lines.add("Time: " + java.time.LocalTime.now());
        lines.add("");
        lines.add("QTY;DESCRIPTION;PRICE;TOTAL");
        double totalWithoutDiscount = 0.0;
        double totalDiscount = 0.0;
        for (OrderThing thing : check.getOrderThings()) {
            Product product = check.findProductById(thing.getProductId());
            if (product != null) {
                double total = product.getPrice() * thing.getQuantity();
                double discount = product.isWholesaleProduct() && thing.getQuantity() >= 5 ?
                        total * 0.1 : total * check.getDiscountPercentage() / 100;
                totalWithoutDiscount += total;
                totalDiscount += discount;
                lines.add(thing.getQuantity() + ";" + product.getDescription() + ";" +
                        String.format("%.2f", product.getPrice()) + ";" +
                        String.format("%.2f", total));
            }
        }
        lines.add("");
        lines.add("Total without discount: " + String.format("%.2f", totalWithoutDiscount));
        lines.add("Total discount: " + String.format("%.2f", totalDiscount));
        lines.add("Total with discount: " + String.format("%.2f", totalWithoutDiscount - totalDiscount));
        lines.add("Discount card: " + check.getDiscountCardNumber());
        lines.add("Debit card balance: " + String.format("%.2f", check.getDebitCardBalance()));
        try {
            lines.add(check.hasEnoughBalance() ? "Transaction approved" : "NOT ENOUGH MONEY");
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        }

        return lines;
    }

    private static String generateCheckString(Check check) throws NotEnoughMoneyException {
        StringBuilder sb = new StringBuilder();
        sb.append("Date: ").append(java.time.LocalDate.now()).append("\n");
        sb.append("Time: ").append(java.time.LocalTime.now()).append("\n");
        sb.append("\n");
        sb.append("QTY;DESCRIPTION;PRICE;TOTAL\n");
        double totalWithoutDiscount = 0.0;
        double totalDiscount = 0.0;
        for (OrderThing thing : check.getOrderThings()) {
            Product product = check.findProductById(thing.getProductId());
            if (product != null) {
                double total = product.getPrice() * thing.getQuantity();
                double discount = product.isWholesaleProduct() && thing.getQuantity() >= 5 ?
                        total * 0.1 : total * check.getDiscountPercentage() / 100;
                totalWithoutDiscount += total;
                totalDiscount += discount;
                sb.append(thing.getQuantity()).append(";")
                        .append(product.getDescription()).append(";")
                        .append(String.format("%.2f", product.getPrice())).append(";")
                        .append(String.format("%.2f", total)).append("\n");
            }
        }
        sb.append("\n");
        sb.append("Total without discount: ").append(String.format("%.2f", totalWithoutDiscount)).append("\n");
        sb.append("Total discount: ").append(String.format("%.2f", totalDiscount)).append("\n");
        sb.append("Total with discount: ").append(String.format("%.2f", totalWithoutDiscount - totalDiscount)).append("\n");
        sb.append("Discount card: ").append(check.getDiscountCardNumber()).append("\n");
        sb.append("Debit card balance: ").append(String.format("%.2f", check.getDebitCardBalance())).append("\n");
        sb.append(check.hasEnoughBalance() ? "Transaction approved" : "NOT ENOUGH MONEY").append("\n");

        return sb.toString();
    }
}
