package ru.clevertec.check;

import ru.clevertec.check.reader.DiscountCardsReader;
import ru.clevertec.check.reader.ProductReader;

import java.nio.file.Paths;
import java.util.List;

public class CheckRunner {
    public static void main(String[] args) {
        try {
            ArgumentParser parser = new ArgumentParser(args);
            List<OrderThing> orderThings = parser.getOrderThings();
            String discountCardNumber = parser.getDiscountCardNumber();
            double debitCardBalance = parser.getDebitCardBalance();

            List<Product> products = ProductReader.readProducts("./src/main/resources/products.csv");
            List<DiscountCards> discountCards = DiscountCardsReader.readDiscountCards("./src/main/resources/discountCards.csv");

            Check check = new Check(orderThings, products, discountCards, discountCardNumber, debitCardBalance);
            CheckPrinter.printToConsole(check);
            CheckPrinter.printToFile(check, Paths.get("result.csv"));
        } catch (BadRequestException e) {
            ExceptionHandler.handle(e, "BAD REQUEST");
        } catch (Exception e) {
            ExceptionHandler.handle(e, "INTERNAL SERVER ERROR");
        }
    }
}
