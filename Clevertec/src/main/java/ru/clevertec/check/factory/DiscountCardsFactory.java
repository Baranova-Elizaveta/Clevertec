package ru.clevertec.check.factory;

import ru.clevertec.check.DiscountCards;

public class DiscountCardsFactory {
    public static DiscountCards createDiscountCard(String number, double discountPercentage){
        return new DiscountCards(number, discountPercentage);
    }
}
