package ru.clevertec.check;

public class DiscountCards {
    private final String number;
    private final double discountPercentage;

    public DiscountCards(String number, double discountPercentage){
        this.number = number;
        this.discountPercentage = discountPercentage;
    }

    public String getNumber() {
        return number;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "number='" + number + '\'' +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}
