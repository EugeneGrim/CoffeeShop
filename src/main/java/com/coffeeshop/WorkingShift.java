package com.coffeeshop;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
class WorkingShift {
    private Date startShiftTime;
    private Date endsShiftTime;

    enum ShiftStatus {
        STARTED,
        CLOSED
    }
    private ShiftStatus shiftStatus;

    private List<Order> ordersList = new ArrayList<>();

    WorkingShift() {
        startShiftTime = new Date();
        shiftStatus = ShiftStatus.STARTED;
        Order.resetOrdersCount();
    }

    WorkingShift addOrder(Order order) {
        if (order.getStatus() == Order.Status.SERVICED || order.getStatus() == Order.Status.CANCELED) {
            ordersList.add(order);
        }
        return this;
    }

    double getProceeds() {
        return ordersList.stream()
                .filter(order -> order.getStatus() == Order.Status.SERVICED)
                .mapToDouble(Order::getPrice)
                .sum();
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy EE");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");

        return "WORKING SHIFT " +
                dateFormat.format(startShiftTime) + System.lineSeparator() +
                "====================" + System.lineSeparator() +
                "Shift status: " + shiftStatus + System.lineSeparator() +
                "Shift starts at: " +
                timeFormat.format(startShiftTime) + System.lineSeparator() +
                "Shift ends at: " +
                (shiftStatus == ShiftStatus.CLOSED ? timeFormat.format(endsShiftTime) : "") +
                System.lineSeparator() + "--------------------" + System.lineSeparator() +
                "Total orders: " + ordersList.size() + System.lineSeparator() +
                "TOTAL PROCEEDS: " + getProceeds() + " RUB" + System.lineSeparator() +
                "--------------------" + System.lineSeparator() + System.lineSeparator();
    }

    void close() {
        endsShiftTime = new Date();
        shiftStatus = ShiftStatus.CLOSED;
    }
}
