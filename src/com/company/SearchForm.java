package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchForm extends JFrame {
    private JTextField textField1;
    private JPanel panel1;
    private JButton okButton;
    private JButton exitButton;
    private JLabel Message;
    private Storage storage = new Storage();

    public SearchForm()
    {
        super("Search");
        this.setVisible(false);
        this.pack();
        this.add(panel1);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setBounds(800, 400, 900, 500);
        textField1.setToolTipText("Input the name of goods");
        textField1.setText("Search...");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = String.valueOf(storage.search(textField1.getText()));
                if (str == "null")
                    Message.setText("There is no such good");
                else
                    Message.setText(str);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("Search...");
                SearchForm.this.setVisible(false);
                Message.setText("");
            }
        });
    }
}
