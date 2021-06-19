package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private JPanel Panel;
    private JButton Groups;
    private JButton Goods;
    private JButton Storage;
    private JButton Search;
    private JButton Display;
    private JTextField searchField;
    private SearchForm searchForm;
    private StorageManagement storageManagement;
    private DisplayForm displayForm;
    private GroupManagement groupManagement;
    private GoodsManagement goodsManagement;

    public GUI()
    {
        super("Manager");
        this.pack();
        setContentPane(Panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(900, 500, 450, 500);
        setVisible(true);
        setup();
        buttons();
    }

    private void setup() {
       searchForm = new SearchForm();
       storageManagement = new StorageManagement();
       displayForm = new DisplayForm();
       groupManagement = new GroupManagement();
       goodsManagement = new GoodsManagement();
    }

    private void buttons() {
        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchForm.setVisible(true);

            }
        });

        Storage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storageManagement.setVisible(true);
            }
        });

        Display.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayForm.setVisible(true);
            }
        });

        Groups.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groupManagement.setVisible(true);
            }
        });
        Goods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodsManagement.setVisible(true);
            }
        });
    }
}
