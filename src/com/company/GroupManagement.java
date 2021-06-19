package com.company;

import javax.swing.*;
import javax.xml.stream.FactoryConfigurationError;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GroupManagement extends JFrame {

    private JButton addButton;
    private JButton changeButton;
    private JButton removeButton;
    private JPanel panel1;
    private JTextField NameField;
    private JTextField DescField;
    private JButton okButton;
    private JLabel Message;
    private JButton endButton;
    private int mode, mode1 = 0;
    private Storage storage = new Storage();
    private String name = "";

    public GroupManagement() {
        super("Group Management");
        this.add(panel1);
        setVisible(false);
        this.pack();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        NameField.setVisible(false);
        DescField.setVisible(false);
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
                NameField.setVisible(true);
                DescField.setVisible(true);
                okButton.setVisible(true);
                refresh();
            }
        });
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = 2;
                mode1 = 0;
                NameField.setVisible(true);
                DescField.setVisible(false);
                okButton.setVisible(true);
                refresh();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = 3;
                mode1 = 0;
                NameField.setVisible(true);
                DescField.setVisible(false);
                okButton.setVisible(true);
                refresh();
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mode == 1) {
                    if (storage.isInArray(NameField.getText())) {
                        Message.setText("The group with this name already exists");
                    } else {
                        Group group = new Group(NameField.getText(), DescField.getText());
                        try {
                            storage.add(group);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        Message.setText("Done");
                    }

                    NameField.setVisible(false);
                    DescField.setVisible(false);
                    okButton.setVisible(false);
                    refresh();
                } else if (mode == 2) {
                    if (mode1 == 0) {
                        int count = 0;
                        String desc = "";
                        name = NameField.getText();

                        for (int i = 0; i < storage.getGroups().length; i++) {
                            if (storage.getGroups()[i].getName().equalsIgnoreCase(name)) {
                                count++;
                                desc = storage.getGroups()[i].getDescription();
                            }
                        }
                        if (count == 0) {
                            mode1 = 0;
                            Message.setText("There is no such a group");
                            NameField.setVisible(false);
                            DescField.setVisible(false);
                            okButton.setVisible(false);
                            refresh();
                        } else {
                            DescField.setVisible(true);
                            DescField.setText(desc);
                            mode1 = 1;
                            Message.setText("Edit now");
                        }


                    } else {
                        mode1 = 0;
                        if (storage.isInArray(NameField.getText()) && !NameField.getText().equalsIgnoreCase(name)){
                            Message.setText("Such group already exists");
                        }
                        else{
                            try {
                                storage.change(name, NameField.getText(), DescField.getText());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            Message.setText("Done");
                        }
                        NameField.setVisible(false);
                        DescField.setVisible(false);
                        okButton.setVisible(false);
                        refresh();
                    }

                } else {
                    if (storage.isInArray(NameField.getText())){
                        try {
                            storage.remove(NameField.getText());
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        Message.setText("Done");
                    }
                    else
                        Message.setText("There is no such group");
                    NameField.setVisible(false);
                    DescField.setVisible(false);
                    okButton.setVisible(false);

                    refresh();
                }

            }
        });

        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
                NameField.setVisible(false);
                DescField.setVisible(false);
                okButton.setVisible(false);
                Message.setText("");
                GroupManagement.this.setVisible(false);
            }
        });
    }

    private void refresh() {
        NameField.setText("Name...");
        DescField.setText("Description...");
    }
}
