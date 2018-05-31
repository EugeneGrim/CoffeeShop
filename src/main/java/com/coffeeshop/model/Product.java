package com.coffeeshop.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public interface Product {
    double getPrice();

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
}
