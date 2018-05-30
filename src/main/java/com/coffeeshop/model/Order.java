package com.coffeeshop.model;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Order {
    private int order_id;
    enum Status {
        CREATED,
        SERVICED,
        CANCELED,
        ERROR

    }
    @Getter
    private Status status;

    @Getter
    private Date orderTime;
    @Getter
    private String errorDescription;

    private LinkedList<Beverage> beverageList = new LinkedList<>();
    private LinkedList<Snack> snackList = new LinkedList<>();
    private LinkedList<HotFood> hotFoodList = new LinkedList<>();

    public Order(int order_id) {
        this.order_id = order_id;
        status = Status.CREATED;
        orderTime = new Date();
    }

    public Order close() {
        status = Status.SERVICED;
        orderTime = new Date();
        return this;
    }

    public Order cancel() {
        status = Status.CANCELED;
        orderTime = new Date();
        return this;
    }

    public Order setErrorState(String errorStateDescription) {
        errorDescription = errorStateDescription;
        status = Status.ERROR;
        return this;
    }

    public Order addBeverage(Beverage beverage) {
        beverageList.add(beverage);
        return this;
    }

    public Order addSnack(Snack snack) {
        snackList.add(snack);
        return this;
    }

    public Order addHotFood(HotFood hotFood) {
        hotFoodList.add(hotFood);
        return this;
    }

    public Order removeBeverage(Beverage beverage) {
        beverageList.remove(beverage);
        return this;
    }

    public Order removeLastBeverage() {
        if (!beverageList.isEmpty()) {
            beverageList.removeLast();
        }
        return this;
    }

    public Order removeSnack(Snack snack) {
        snackList.remove(snack);
        return this;
    }

    public Order removeLastSnack() {
        if (!snackList.isEmpty()) {
            snackList.removeLast();
        }
        return this;
    }public Order removeHotFood(HotFood hotFood) {
        hotFoodList.remove(hotFood);
        return this;
    }

    public Order removeLastHotFood() {
        if (!hotFoodList.isEmpty()) {
            hotFoodList.removeLast();
        }
        return this;
    }

    double getPrice() {
        return beverageList.stream().mapToDouble(Beverage::getPrice).sum() +
                snackList.stream().mapToDouble(Snack::getPrice).sum() +
                hotFoodList.stream().mapToDouble(HotFood::getPrice).sum();
    }

    private String getStringGoodsInOerder(LinkedList<? extends Goods> goods, String title) {
        StringBuilder result = new StringBuilder(title).append(System.lineSeparator());
        for (Goods good: goods) {
            result.append("- ").append(good)
                    .append(", ")
                    .append(good.getPrice())
                    .append(" RUB")
                    .append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm EE dd.MM.yyyy");
        StringBuilder result = new StringBuilder("Order " + (order_id))
                .append(" - ").append(status).append(" ")
                .append(dateFormat.format(orderTime))
                .append(System.lineSeparator())
                .append("===================")
                .append(System.lineSeparator());

        if (!beverageList.isEmpty()) {
            result.append(getStringGoodsInOerder(beverageList, "Drinks:"));
        }

        if (!hotFoodList.isEmpty()) {
            result.append(getStringGoodsInOerder(hotFoodList, "Hot food:"));
        }

        if (!snackList.isEmpty()) {
            result.append(getStringGoodsInOerder(snackList, "Snacks:"));
        }

        result.append("-------------------")
                .append(System.lineSeparator())
                .append("TOTAL PRICE: ")
                .append(this.getPrice())
                .append(" RUB").append(System.lineSeparator())
                .append("-------------------")
                .append(System.lineSeparator());

        return result.toString();
    }
}
