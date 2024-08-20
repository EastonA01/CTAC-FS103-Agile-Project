package org.example.restaurant.controller;

/*
Connects the GUI with the InventoryService.
 */

import org.example.restaurant.service.InventoryService;
import org.example.restaurant.gui.InventoryManagementPanel;
import org.example.restaurant.model.InventoryItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InventoryController {
    private InventoryService inventoryService;
    private InventoryManagementPanel inventoryManagementPanel;

    public InventoryController(InventoryService inventoryService, InventoryManagementPanel inventoryManagementPanel) {
        this.inventoryService = inventoryService;
        this.inventoryManagementPanel = inventoryManagementPanel;

        // TODO: Set up action listeners for inventory management panel
        setupActionListeners();
        loadInventoryTable();
    }

    // TODO: Implement methods to interact with InventoryService
    private void setupActionListeners() {
        inventoryManagementPanel.getRestockButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restockItem();
            }
        });
    }

    private void restockItem() {
        // Collect data from GUI
        String ingredientName = inventoryManagementPanel.getIngredientNameField().getText();
        int quantity;
        try {
            quantity = Integer.parseInt(inventoryManagementPanel.getQuantityField().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(inventoryManagementPanel, "Please enter a valid quantity.");
            return;
        }

        // Update inventory via the service
        inventoryService.updateInventoryItem(ingredientName, quantity);

        // Update GUI
        loadInventoryTable();
        JOptionPane.showMessageDialog(inventoryManagementPanel, "Inventory updated successfully!");
    }

    private void loadInventoryTable() {
        // Retrieve all inventory items from the service
        List<InventoryItem> inventoryItems = inventoryService.getAllInventoryItems();

        // Prepare data for the table
        String[] columnNames = {"Ingredient Name", "Quantity"};
        Object[][] data = new Object[inventoryItems.size()][2];
        for (int i = 0; i < inventoryItems.size(); i++) {
            data[i][0] = inventoryItems.get(i).getIngredientName();
            data[i][1] = inventoryItems.get(i).getQuantity();
        }

        // Update the table in the GUI
        inventoryManagementPanel.updateInventoryTable(data, columnNames);
    }
}
