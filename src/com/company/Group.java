package com.company;

import java.io.IOException;

public class Group {

    private Storage storage = new Storage();

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void add(Good good) throws IOException {
        for (int i = 0; i < storage.getAllGoods().length; i++) {
            if (good.getName().equalsIgnoreCase(storage.getAllGoods()[i].getName()))
                return;
        }
        Good[] temp = new Good[goods.length + 1];
        for (int i = 0; i < goods.length; i++) {
            temp[i] = goods[i];
        }
        temp[temp.length - 1] = good;
        goods = temp;
        Storage.toFile();
    }

    public void change(String name, String newName, String description, String manufacturer, int amount, int price) throws IOException {
        int count = 0;
        for (int i = 0; i < storage.getAllGoods().length; i++) {
            if (storage.getAllGoods()[i].getName().equalsIgnoreCase(newName))
                count++;
        }
        for (int i = 0; i < goods.length; i++) {
            if (goods[i].getName().equalsIgnoreCase(name) & count == 0) {
                goods[i].setPrice(price);
                goods[i].setAmount(amount);
                goods[i].setName(newName);
                goods[i].setManufacturer(manufacturer);
                goods[i].setDescription(description);
                break;
            } else if (goods[i].getName().equalsIgnoreCase(name) & count != 0) {
                goods[i].setPrice(price);
                goods[i].setAmount(amount);
                goods[i].setManufacturer(manufacturer);
                goods[i].setDescription(description);
                break;
            }
        }
        Storage.toFile();
    }

    public void remove(String name) throws IOException {
        Good[] temp = new Good[goods.length - 1];
        int count = 0;
        for (int i = 0; i < temp.length; i++) {
            if (goods[count].getName().equalsIgnoreCase(name))
                count++;
            temp[i] = goods[count];
            count++;
        }
        goods = temp;
        Storage.toFile();
    }

    public void removeAll() {
        goods = new Good[0];
    }

    private String name, description;
    private Good[] goods = new Good[0];

    public Good[] getGoods() {
        return goods;
    }

    public void setGoods(Good[] goods) {
        this.goods = goods;
    }

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

    @Override
    public String toString() {
        String result = "Group " + name + " (" + description + " ) \n";
        for (int i = 0; i < goods.length; i++)
            result += goods[i];
        return result;
    }

}
