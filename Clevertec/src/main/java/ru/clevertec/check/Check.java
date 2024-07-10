package ru.clevertec.check;

import java.util.List;

public class Check {
    private final List<OrderThing> orderThings;
    private final List<Product> products;
    private final List<DiscountCards> discountCards;
    private final String discountCardNumber;
    private final double debitCardBalance;

    public Check(List<OrderThing> orderThings, List<Product> products, List<DiscountCards> discountCards, String discountCardNumber, double debitCardBalance) {
        this.orderThings = orderThings;
        this.products = products;
        this.discountCards = discountCards;
        this.discountCardNumber = discountCardNumber;
        this.debitCardBalance = debitCardBalance;
    }

    public List<OrderThing> getOrderThings() {
        return orderThings;
    }

    public String getDiscountCardNumber() {
        return discountCardNumber;
    }

    public double getDebitCardBalance() {
        return debitCardBalance;
    }

    public double getDiscountPercentage() {
        for (DiscountCards card : discountCards) {
            if (card.getNumber().equals(discountCardNumber)) {
                return card.getDiscountPercentage();
            }
        }
        return 2.0;
    }

    public Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public boolean hasEnoughBalance() throws NotEnoughMoneyException {
        double totalCost = 0.0;
        for (OrderThing item : orderThings) {
            Product product = findProductById(item.getProductId());
            if (product != null) {
                double total = product.getPrice() * item.getQuantity();
                double discount = product.isWholesaleProduct() && item.getQuantity() >= 5 ?
                        total * 0.1 : total * getDiscountPercentage() / 100;
                totalCost += total - discount;
            }
        }

        if (debitCardBalance < totalCost) {
            throw new NotEnoughMoneyException("Insufficient funds on the debit card.");
        }

        return true;
    }
}
