package ru.clevertec.check.reader;

import ru.clevertec.check.DiscountCards;
import ru.clevertec.check.factory.DiscountCardsFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiscountCardsReader {
    public static List<DiscountCards> readDiscountCards(String filePath) throws IOException{
        List<DiscountCards> discountCards = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                String number = values[0];
                double discountPercentage = Double.parseDouble(values[1]);
                discountCards.add(DiscountCardsFactory.createDiscountCard(number, discountPercentage));
            }
        }
        return discountCards;
    }
}
