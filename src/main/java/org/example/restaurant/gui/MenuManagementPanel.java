package org.example.restaurant.gui;

/*
Interface to manage menu items.
 */

import javax.swing.*;
import java.awt.*;

public class MenuManagementPanel extends JPanel {
    // Components like tables, text fields, buttons
    private JTable menuTable;
    private JTextField itemNameField;
    private JTextArea itemDescriptionArea;
    private JTextField itemPriceField;
    private JTextField itemIngredientsField;
    private JButton addButton;
    private JButton removeButton;
    private JButton editButton;
    private JButton viewButton;
    private JTextArea menuDisplayArea;

    public MenuManagementPanel() {
        // TODO: Initialize components
        itemNameField = new JTextField(20);
        itemDescriptionArea = new JTextArea(3, 20);
        itemPriceField = new JTextField(10);
        itemIngredientsField = new JTextField(30);
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        editButton = new JButton("Edit");
        viewButton = new JButton("View");
        menuDisplayArea = new JTextArea(10, 30);
        menuDisplayArea.setEditable(false);

        // TODO: Add components to panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Item Name:"), gbc);

        gbc.gridx = 1;
        add(itemNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Description:"), gbc);

        gbc.gridx = 1;
        add(new JScrollPane(itemDescriptionArea), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Price:"), gbc);

        gbc.gridx = 1;
        add(itemPriceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Ingredients (separated by ;):"), gbc);

        gbc.gridx = 1;
        add(itemIngredientsField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(addButton, gbc);

        gbc.gridy = 5;
        add(removeButton, gbc);

        gbc.gridy = 6;
        add(editButton, gbc);

        gbc.gridy = 7;
        add(viewButton, gbc);

        gbc.gridy = 8;
        add(new JScrollPane(menuDisplayArea), gbc);

        // TODO: Set up action listeners for buttons
        // Note: The action listeners will be set up in the MenuController, so nothing is needed here
    }

    // TODO: Implement methods to interact with the service layer
    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public JTextField getNameField() {
        return itemNameField;
    }

    public JTextArea getDescriptionField() {
        return itemDescriptionArea;
    }

    public JTextField getPriceField() {
        return itemPriceField;
    }

    public JTextField getIngredientsField() {
        return itemIngredientsField;
    }

    public JTextArea getMenuDisplayArea() {
        return menuDisplayArea;
    }
}
