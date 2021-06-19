package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayForm extends JFrame {

    private JButton storageInfoButton;
    private JButton generalGroupValueButton;
    private JButton groupsInfoButton;
    private JButton generalStorageValueButton;
    private JLabel Info;
    private JPanel panel1;
    private JButton endButton;
    private Storage storage = new Storage();

    public DisplayForm()
    {
        super("Display");
        setVisible(false);
        this.add(panel1);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        buttons();
        setBounds(700, 300, 700, 700);

    }

    private void buttons() {
        storageInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(DisplayForm.this, storage.StorageInfo());
            }
        });

        groupsInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(DisplayForm.this, storage);
            }
        });

        generalStorageValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(DisplayForm.this, storage.GeneralStorageValue());
            }
        });

        generalGroupValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(DisplayForm.this, storage.GeneralGroupValue());
            }
        });

        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayForm.this.setVisible(false);
            }
        });
    }
}
