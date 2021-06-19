package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private static Group[] groups = new Group[0];

    public static Group[] getGroups() {
        return groups;
    }

    public static void setGroups(Group[] groups) {
        Storage.groups = groups;
    }

    public Group getCertainGroup(String name){
        for (int i = 0; i < groups.length; i++)
            if (groups[i].getName().equalsIgnoreCase(name))
                return groups[i];
        return null;
    }
    public boolean isInArray(String name){
        for (int i = 0; i < groups.length; i++){
            if (name.equalsIgnoreCase(groups[i].getName()))
                return true;
        }
        return false;
    }

    //Tim 8
    public Good search(String name) {
        return null;
    }

    public Good[] getAllGoods() {
        Good[] all = new Good[0];
        for (int i = 0; i < groups.length; i++) {
            for (int c = 0; c < groups[i].getGoods().length; c++) {
                all = addToArray(all, groups[i].getGoods()[c]);
            }
        }
        return all;
    }

    private Good[] addToArray(Good[] goodArray, Good good) {
        Good[] test = new Good[goodArray.length + 1];
        for (int i = 0; i < goodArray.length; i++)
            test[i] = goodArray[i];
        test[test.length - 1] = good;
        return test;
    }

    public void add(Group group) throws IOException {
        Group[] temp = new Group[groups.length + 1];
        for (int i = 0; i < groups.length; i++) {
            temp[i] = groups[i];
        }
        temp[temp.length - 1] = group;
        groups = temp;
        toFile();
    }

    public void change(String name, String newName, String newDescription) throws IOException {
        for (int i = 0; i < groups.length; i++) {
            if (groups[i].getName().equalsIgnoreCase(name)) {
                groups[i].setName(newName);
                groups[i].setDescription(newDescription);
                break;
            }
        }
        toFile();
    }

    public void remove(String name) throws IOException {
        Group[] temp = new Group[groups.length - 1];
        int count = 0;
        for (int i = 0; i < temp.length; i++) {
            if (groups[count].getName().equalsIgnoreCase(name))
                count++;
            temp[i] = groups[count];
            count++;
        }
        groups = temp;
        toFile();
    }

    public void manageAmount(String name, int amount) throws IOException {
        for (int i = 0; i < groups.length; i++) {
            for (int j = 0; j < groups[i].getGoods().length; j++) {
                int temp = groups[i].getGoods()[j].getAmount() + amount;
                if (groups[i].getGoods()[j].getName().equals(name)) {
                    if (temp >= 0) {
                        groups[i].getGoods()[j].setAmount(temp);
                    } else {
                        groups[i].getGoods()[j].setAmount(0);
                    }
                }
            }
        }
    }

    //Tim 9
    public String StorageInfo()
    {
        String result = "";
        for (int i = 0; i < getAllGoods().length; i++)
            result += getAllGoods()[i] + "\n";
        return result;

    }

    public String GeneralStorageValue()
    {
        String result = "";
        for (int i = 0; i < getAllGoods().length; i++){
            result += getAllGoods()[i].getName() + " - " + (getAllGoods()[i].getAmount() * getAllGoods()[i].getPrice()) + " грн " + "\n";
        }
        return result;
    }

    public String GeneralGroupValue()
    {
        String result = "";
        int price;
        for (int i = 0; i < groups.length; i++){
            result += groups[i].getName();
            price = 0;
            for (int c = 0; c < groups[i].getGoods().length; c++){
                price += groups[i].getGoods()[c].getPrice() * groups[i].getGoods()[c].getAmount();
            }
            result += " " + price + " грн \n";
        }
        return result;
    }


    public static void toFile() throws IOException {
        String group = "", good = "";

        for (int i = 0; i < groups.length; i++) {
            group += groups[i].getName() + "\n";
            good = "";
            for (int c = 0; c < groups[i].getGoods().length; c++) {
                good += groups[i].getGoods()[c] + "\n";
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(groups[i].getName() + ".txt"));
            writer.write(good);
            writer.close();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("Groups.txt"));
        writer.write(group);
        writer.close();
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < groups.length; i++)
            result += groups[i] + "\n";
        return result;
    }

}
