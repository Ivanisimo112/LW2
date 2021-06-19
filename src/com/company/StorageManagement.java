package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.IOException;

public class StorageManagement extends JFrame {
    private JPanel panel1;
    private JTextField Name;
    private JTextField Amount;
    private JButton proceedButton;
    private JButton endButton;
    private JLabel Message;
    private Storage storage = new Storage();

    public StorageManagement() {
        super("Storage Management");
        setVisible(false);
        this.add(panel1);
        setBounds(900, 500, 600, 500);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        endButton.addComponentListener(new ComponentAdapter() {
        });
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Name.setText("");
                Amount.setText("");
                Message.setText("");
                setVisible(false);
            }
        });
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = -1;
                int count = 0;
                try {
                    amount = Integer.valueOf(Amount.getText());
                    for (int i = 0; i < storage.getAllGoods().length; i++) {
                        int temp = storage.getAllGoods()[i].getAmount() + amount;
                        if (storage.getAllGoods()[i].getName().equalsIgnoreCase(Name.getText())) {
                            if (temp >= 0) {
                                storage.getAllGoods()[i].setAmount(temp);
                                Message.setText("Done");
                                count++;
                                break;
                            } else {
                                Message.setText("There is no so many goods in the storage");
                                count++;
                                break;
                            }
                        }
                    }
                    if (count == 0)
                        Message.setText("There is no such good");
                } catch (NumberFormatException w) {
                    Message.setText("Wrong numbers input");
                }


            }
        });
    }
}
