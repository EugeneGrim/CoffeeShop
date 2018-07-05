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
    @Getter
    private LinkedList<Product> productList = new LinkedList<>();

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

    public Order addProduct(Product product) {
        this.productList.add(product);
        return this;
    }

    public Order removeProduct(Product product) {
        this.productList.remove(product);
        return this;
    }

    public Order removeLastProduct() {
        this.productList.removeLast();
        return this;
    }

    double getPrice() {
        return productList.stream().mapToDouble(Product::getPrice).sum();
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
        for (Product product : productList) {
            result.append("- ").append(product)
                    .append(", ")
                    .append(product.getPrice())
                    .append(" RUB")
                    .append(System.lineSeparator());
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
