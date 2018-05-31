package com.coffeeshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract class Goods implements Product {
    private String name;
    private String description;
    private double cost;

    @Override
    public String toString() {
        return name + " " + description;
    }
}
