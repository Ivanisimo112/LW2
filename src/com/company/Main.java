package com.company;

import java.io.IOException;

public class Main {

    private static Storage storage = new Storage();

    public static void main(String[] args) throws IOException {
        Group groceries = new Group("Groceries", "Food");
        Good milk = new Good("Milk", "cow's product", "Bohdan Zverek", 30, 10);
        Good sausage = new Good("Sausage", "meat product", "Bogdock Zverian", 50, 20);
        Group toys = new Group("Toys", "Children's joy");
        Good car = new Good("Car", "toy", "LEGO", 80, 40);
        Good bear = new Good("Bear", "Stuffed Animal Bear", "Noname", 10, 25);
        Good popIt = new Good("Pop it", "better than simple dimple", "hz", 120, 100);
        Good simpleDimple = new Good("Simple Dimple", "better than pop it", "hz", 110, 150);
        storage.add(groceries);
        storage.add(toys);
        groceries.add(milk);
        groceries.add(sausage);
        toys.add(car);
        toys.add(bear);
        toys.add(popIt);
        toys.add(simpleDimple);
        new GUI();
    }
}
