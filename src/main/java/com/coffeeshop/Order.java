package com.coffeeshop;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

class Order {
    private static int ordersCount;
    private int order_id;

    enum Status {
        CREATED,
        SERVICED,
        CANCELED
    }

    @Getter
    private Status status;
    @Getter
    private Date orderTime;

    Order() {
        order_id = ordersCount;
        ordersCount++;
        status = Status.CREATED;
        orderTime = new Date();
    }

    Order close() {
        status = Status.SERVICED;
        orderTime = new Date();
        return this;
    }

    Order cancel() {
        status = Status.CANCELED;
        orderTime = new Date();
        return this;
    }

    static void resetOrdersCount() {
        ordersCount = 0;
    }

    private LinkedList<Beverage> beverageList = new LinkedList<>();

    Order addBeverage(Beverage beverage) {
        beverageList.add(beverage);
        return this;
    }

    Order removeBeverage(Beverage beverage) {
        beverageList.remove(beverage);
        return this;
    }

    Order removeLastBeverage() {
        if (!beverageList.isEmpty()) {
            beverageList.removeLast();
        }
        return this;
    }

    double getPrice() {
        return beverageList.stream().mapToDouble(Beverage::getPrice).sum();
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm EE dd.MM.yyyy");
        StringBuilder result = new StringBuilder("Order N" + (order_id + 1))
                .append(" - ").append(status).append(" ")
                .append(dateFormat.format(orderTime))
                .append(System.lineSeparator())
                .append("===================")
                .append(System.lineSeparator())
                .append("Drinks:").append(System.lineSeparator());
        for (Beverage beverage : beverageList) {
            result.append("- ").append(beverage)
                    .append(", ")
                    .append(beverage.getPrice())
                    .append(" RUB")
                    .append(System.lineSeparator());
        }
        result.append(System.lineSeparator())
                .append("-------------------")
                .append(System.lineSeparator())
                .append("TOTAL PRICE: ")
                .append(this.getPrice())
                .append(" RUB").append(System.lineSeparator())
                .append("-------------------")
                .append(System.lineSeparator());

        return result.toString();
    }
}
