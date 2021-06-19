package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GoodsManagement extends JFrame {

    private JPanel panel1;
    private JButton addButton;
    private JButton changeButton;
    private JButton removeButton;
    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private JTextField manufacturerTextField;
    private JTextField amountTextField;
    private JTextField priceTextField;
    private JButton okButton;
    private JButton endButton;
    private JLabel Message;
    private JTextField groupTextField;
    private int mode, mode1 = 0;
    private Storage storage = new Storage();
    private Group group;
    private String nameS;

    public GoodsManagement() {
        super("Goods Management");
        this.add(panel1);
        setVisible(false);
        this.pack();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        groupTextField.setVisible(false);
        nameTextField.setVisible(false);
        descriptionTextField.setVisible(false);
        manufacturerTextField.setVisible(false);
        amountTextField.setVisible(false);
        priceTextField.setVisible(false);
        okButton.setVisible(false);
        setBounds(900, 500, 500, 300);
        buttons();
    }

    private void buttons() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = 1;
                mode1 = 0;
                groupTextField.setVisible(true);
                nameTextField.setVisible(true);
                descriptionTextField.setVisible(true);
                manufacturerTextField.setVisible(true);
                amountTextField.setVisible(true);
                priceTextField.setVisible(true);
                okButton.setVisible(true);
                refresh();
            }
        });
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = 2;
                mode1 = 0;
                nameTextField.setVisible(true);
                groupTextField.setVisible(true);
                descriptionTextField.setVisible(false);
                manufacturerTextField.setVisible(false);
                amountTextField.setVisible(false);
                priceTextField.setVisible(false);
                okButton.setVisible(true);
                refresh();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = 3;
                mode1 = 0;
                nameTextField.setVisible(true);
                groupTextField.setVisible(true);
                descriptionTextField.setVisible(false);
                manufacturerTextField.setVisible(false);
                amountTextField.setVisible(false);
                priceTextField.setVisible(false);
                okButton.setVisible(true);
                refresh();
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mode == 1) {
                    int count = 0;
                    for (int i = 0; i < storage.getGroups().length; i++) {
                        if (storage.getGroups()[i].getName().equalsIgnoreCase(groupTextField.getText()))
                            count++;
                    }
                    if (count == 0)
                        Message.setText("There is no such group");
                    else {
                        count = 0;
                        for (int i = 0; i < storage.getAllGoods().length; i++) {
                            if (storage.getAllGoods()[i].getName().equalsIgnoreCase(nameTextField.getText()))
                                count++;
                        }
                        if (count > 0)
                            Message.setText("Such good already exists");
                        else {
                            groupTextField.setVisible(false);
                            nameTextField.setVisible(false);
                            descriptionTextField.setVisible(false);
                            manufacturerTextField.setVisible(false);
                            amountTextField.setVisible(false);
                            priceTextField.setVisible(false);
                            okButton.setVisible(false);
                            Message.setText("Done");
                            int amount = -1, price = -1;
                            try {
                                amount = Integer.valueOf(amountTextField.getText());
                                price = Integer.valueOf(priceTextField.getText());
                                if (amount < 0 | price <= 0) {
                                    Message.setText("Wrong numbers input");
                                } else {
                                    Good good = new Good(nameTextField.getText(), descriptionTextField.getText(), manufacturerTextField.getText(), amount, price);
                                    try {
                                        storage.getCertainGroup(groupTextField.getText()).add(good);
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    }
                                    refresh();
                                }
                            } catch (NumberFormatException w) {
                                Message.setText("Wrong numbers input");
                            }
                        }
                    }
                } else if (mode == 2) {
                    if (mode1 == 0) {
                        int count = 0;

                        String groupName = groupTextField.getText();
                        nameS = nameTextField.getText();
                        Good good = new Good("ss", "wd", "asd", 5, 5);

                        for (int i = 0; i < storage.getGroups().length; i++) {
                            if (storage.getGroups()[i].getName().equalsIgnoreCase(groupName)) {
                                group = storage.getGroups()[i];
                                for (int c = 0; c < storage.getGroups()[i].getGoods().length; c++) {
                                    if (storage.getGroups()[i].getGoods()[c].getName().equalsIgnoreCase(nameTextField.getText())) {
                                        count++;
                                        good = storage.getGroups()[i].getGoods()[c];
                                        nameS = storage.getGroups()[i].getGoods()[c].getName();
                                        break;
                                    }
                                }
                            }
                        }

                        if (count == 0) {
                            mode1 = 0;
                            Message.setText("This good has not been found");
                            groupTextField.setVisible(false);
                            nameTextField.setVisible(false);
                            descriptionTextField.setVisible(false);
                            manufacturerTextField.setVisible(false);
                            amountTextField.setVisible(false);
                            priceTextField.setVisible(false);
                            okButton.setVisible(false);
                            refresh();
                        } else {
                            mode1 = 1;
                            Message.setText("Edit now");
                            nameTextField.setText(nameS);
                            groupTextField.setVisible(false);
                            descriptionTextField.setVisible(true);
                            descriptionTextField.setText(good.getDescription());
                            manufacturerTextField.setVisible(true);
                            manufacturerTextField.setText(good.getManufacturer());
                            amountTextField.setVisible(true);
                            amountTextField.setText(String.valueOf(good.getAmount()));
                            priceTextField.setVisible(true);
                            priceTextField.setText(String.valueOf(good.getPrice()));
                        }
                    } else {
                        mode1 = 0;
                        int amount = -1, price = -1;
                        int count = 0;
                        for (int i = 0; i < storage.getAllGoods().length; i++){
                            if (storage.getAllGoods()[i].getName().equalsIgnoreCase(nameTextField.getText()))
                                count++;
                        }
                        if (count == 1 && !nameS.equalsIgnoreCase(nameTextField.getText())){
                            Message.setText("The name is already occupied");
                            groupTextField.setVisible(false);
                            nameTextField.setVisible(false);
                            descriptionTextField.setVisible(false);
                            manufacturerTextField.setVisible(false);
                            amountTextField.setVisible(false);
                            priceTextField.setVisible(false);
                            okButton.setVisible(false);
                            refresh();
                        }
                        else{
                            try {
                                amount = Integer.valueOf(amountTextField.getText());
                                price = Integer.valueOf(priceTextField.getText());
                                if (amount < 0 | price <= 0) {
                                    throw new NumberFormatException();
                                } else {
                                    try {
                                        group.change(nameS, nameTextField.getText(), descriptionTextField.getText(), manufacturerTextField.getText(), amount, price);
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    }
                                    Message.setText("Done");
                                    groupTextField.setVisible(false);
                                    nameTextField.setVisible(false);
                                    descriptionTextField.setVisible(false);
                                    manufacturerTextField.setVisible(false);
                                    amountTextField.setVisible(false);
                                    priceTextField.setVisible(false);
                                    okButton.setVisible(false);
                                    refresh();
                                }
                            } catch (NumberFormatException w) {
                                Message.setText("Wrong numbers input");
                                groupTextField.setVisible(false);
                                nameTextField.setVisible(false);
                                descriptionTextField.setVisible(false);
                                manufacturerTextField.setVisible(false);
                                amountTextField.setVisible(false);
                                priceTextField.setVisible(false);
                                okButton.setVisible(false);
                                refresh();
                            }
                        }
                    }
                } else {
                    int count = 0;
                    Group group = new Group("ss", "dsa");
                    for (int i = 0; i < storage.getGroups().length; i++){
                        if (groupTextField.getText().equalsIgnoreCase(storage.getGroups()[i].getName())) {
                            count++;
                            group = storage.getGroups()[i];
                        }
                    }


                    if (count == 0){
                        groupTextField.setVisible(false);
                        nameTextField.setVisible(false);
                        descriptionTextField.setVisible(false);
                        manufacturerTextField.setVisible(false);
                        amountTextField.setVisible(false);
                        priceTextField.setVisible(false);
                        okButton.setVisible(false);
                        Message.setText("No Group has been found");
                        refresh();
                    }
                    else{
                        for (int i = 0; i < group.getGoods().length; i++){
                            if (group.getGoods()[i].getName().equalsIgnoreCase(nameTextField.getText()))
                                count++;
                        }
                        if (count == 2){
                            try {
                                group.remove(nameTextField.getText());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            groupTextField.setVisible(false);
                            nameTextField.setVisible(false);
                            descriptionTextField.setVisible(false);
                            manufacturerTextField.setVisible(false);
                            amountTextField.setVisible(false);
                            priceTextField.setVisible(false);
                            okButton.setVisible(false);
                            Message.setText("Done");
                            refresh();
                        }
                        else{
                            groupTextField.setVisible(false);
                            nameTextField.setVisible(false);
                            descriptionTextField.setVisible(false);
                            manufacturerTextField.setVisible(false);
                            amountTextField.setVisible(false);
                            priceTextField.setVisible(false);
                            okButton.setVisible(false);
                            Message.setText("No Good has been found");
                            refresh();
                        }
                    }

                }

            }
        });

        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
                nameTextField.setVisible(false);
                groupTextField.setVisible(false);
                descriptionTextField.setVisible(false);
                manufacturerTextField.setVisible(false);
                amountTextField.setVisible(false);
                priceTextField.setVisible(false);
                okButton.setVisible(false);
                Message.setText("");
                GoodsManagement.this.setVisible(false);
            }
        });
    }

    private void refresh() {
        nameTextField.setText("Name...");
        descriptionTextField.setText("Description...");
        manufacturerTextField.setText("Manufacturer...");
        amountTextField.setText("Amount...");
        priceTextField.setText("Price...");
        groupTextField.setText("Group...");
    }
}
