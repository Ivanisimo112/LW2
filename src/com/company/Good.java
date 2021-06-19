package com.company;

public class Good {
    public Good(String name, String description, String manufacturer, int amount, int price) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.amount = amount;
        this.price = price;
    }

    private String name, description, manufacturer;
    private int amount, price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if (amount >= 0)
            this.amount = amount;
        else
            this.amount = 0;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price > 0)
            this.price = price;
        else
            this.price = 1;
    }

    @Override
    public String toString() {
        return "Good " + name + "\n ( " + description + " ) \n Manufacturer: " + manufacturer + "\n Amount: " + amount + "\n Price: " + price + "\n";
    }
}
