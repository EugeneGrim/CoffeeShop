package com.coffeeshop.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
abstract class Goods {
    private String name;
    private String description;
    private double cost;

    abstract double getPrice();

    @Override
    public String toString() {
        return name;
    }
}
