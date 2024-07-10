package ru.clevertec.check;

import java.util.ArrayList;
import java.util.List;

public class ArgumentParser {
    private final List<OrderThing> orderThings = new ArrayList<>();
    private String discountCardNumber;
    private double debitCardBalance;

    public ArgumentParser(String[] args) throws BadRequestException {
        parse(args);
    }

    private void parse(String[] args) throws BadRequestException {
        if (args.length < 2) {
            throw new BadRequestException("Invalid arguments");
        }
        for (String arg : args) {
            if (arg.startsWith("discountCard=")) {
                discountCardNumber = arg.split("=")[1];
            } else if (arg.startsWith("balanceDebitCard=")) {
                debitCardBalance = Double.parseDouble(arg.split("=")[1]);
            } else if (arg.contains("-")) {
                String[] parts = arg.split("-");
                int id = Integer.parseInt(parts[0]);
                int quantity = Integer.parseInt(parts[1]);
                orderThings.add(new OrderThing(id, quantity));
            } else {
                throw new BadRequestException("Invalid argument format");
            }
        }
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
}
