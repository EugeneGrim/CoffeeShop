package com.coffeeshop.model;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class WorkingShift {
    private Date startShiftTime;
    private Date endShiftTime;
    private User user;

    enum ShiftStatus {
        STARTED,
        CLOSED
    }
    private ShiftStatus shiftStatus;

    private List<Order> ordersList = new ArrayList<>();

    public WorkingShift(User user) {
        startShiftTime = new Date();
        shiftStatus = ShiftStatus.STARTED;
        this.user = user;
    }

    public WorkingShift addOrder(Order order) {
        ordersList.add(order);
        return this;
    }

    private double getProceeds() {
        return ordersList.stream()
                .filter(order -> order.getStatus() == Order.Status.SERVICED)
                .mapToDouble(Order::getPrice)
                .sum();
    }

    private int countOfServicedOrders() {
        return ordersList.stream()
                .filter(order -> order.getStatus() == Order.Status.SERVICED)
                .mapToInt(order -> 1)
                .sum();
    }

    private int countOfCanceledOrders() {
        return ordersList.stream()
                .filter(order -> order.getStatus() == Order.Status.CANCELED)
                .mapToInt(order -> 1)
                .sum();
    }

    private int countOfErrorOrders() {
        return ordersList.stream()
                .filter(order -> order.getStatus() == Order.Status.ERROR)
                .mapToInt(order -> 1)
                .sum();
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy EE");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        return "WORKING SHIFT " +
                dateFormat.format(startShiftTime) + System.lineSeparator() +
                "====================" + System.lineSeparator() +
                "Shift status: " + shiftStatus + System.lineSeparator() +
                "Shift starts at: " +
                timeFormat.format(startShiftTime) + System.lineSeparator() +
                "Shift ends at: " +
                (shiftStatus == ShiftStatus.CLOSED ? timeFormat.format(endShiftTime) : "") +
                System.lineSeparator() + "--------------------" + System.lineSeparator() +
                "Total orders: " + ordersList.size() + System.lineSeparator() +
                "Serviced orders: " + countOfServicedOrders() + System.lineSeparator() +
                "Canceled orders: " + countOfCanceledOrders() + System.lineSeparator() +
                "Error orders: " + countOfErrorOrders() + System.lineSeparator() +
                "--------------------" + System.lineSeparator() +
                "TOTAL PROCEEDS: " + getProceeds() + " RUB" + System.lineSeparator() +
                "--------------------" + System.lineSeparator();
    }

    public void close() {
        endShiftTime = new Date();
        shiftStatus = ShiftStatus.CLOSED;
    }
}
