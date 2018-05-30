package com.coffeeshop.model;

import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Setter
@Getter
abstract class Goods {
    private String name;
    private String description;
    private double cost;

    static double setMargin(String marginName) {
        double margin;
        Properties marginProperties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/com/coffeeshop/margin.properties")) {
            marginProperties.load(fileInputStream);
            margin = Double.parseDouble(marginProperties.getProperty(marginName));
        } catch (IOException e) {
            e.printStackTrace();
            margin = 1.0;
        }
        return margin;
    }

    abstract double getPrice();

    @Override
    public String toString() {
        return name;
    }
}
