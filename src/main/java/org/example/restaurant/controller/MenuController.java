package org.example.restaurant.controller;

/*
Connects the GUI with the MenuService.
 */

import org.example.restaurant.service.MenuService;
import org.example.restaurant.gui.MenuManagementPanel;
import org.example.restaurant.model.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuController {
    private MenuService menuService;
    private MenuManagementPanel menuManagementPanel;

    public MenuController(MenuService menuService, MenuManagementPanel menuManagementPanel) {
        this.menuService = menuService;
        this.menuManagementPanel = menuManagementPanel;

        // TODO: Set up action listeners for menu management panel
        setupActionListeners();
    }

    // TODO: Implement methods to interact with MenuService
    private void setupActionListeners() {
        menuManagementPanel.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMenuItem();
            }
        });

        menuManagementPanel.getRemoveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeMenuItem();
            }
        });

        menuManagementPanel.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editMenuItem();
            }
        });

        menuManagementPanel.getViewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllMenuItems();
            }
        });
    }

    private void addMenuItem() {
        // Collect data from GUI
        String name = menuManagementPanel.getNameField().getText();
        String description = menuManagementPanel.getDescriptionField().getText();
        double price = Double.parseDouble(menuManagementPanel.getPriceField().getText());
        List<String> ingredients = List.of(menuManagementPanel.getIngredientsField().getText().split(";"));

        // Create MenuItem and add it to the menu service
        MenuItem menuItem = new MenuItem(name, description, price, ingredients);
        menuService.addMenuItem(menuItem);

        // Update GUI
        JOptionPane.showMessageDialog(menuManagementPanel, "Menu item added successfully!");
    }

    private void removeMenuItem() {
        // Collect data from GUI
        String name = menuManagementPanel.getNameField().getText();

        // Remove MenuItem from the menu service
        menuService.removeMenuItem(name);

        // Update GUI
        JOptionPane.showMessageDialog(menuManagementPanel, "Menu item removed successfully!");
    }

    private void editMenuItem() {
        // Collect data from GUI
        String name = menuManagementPanel.getNameField().getText();
        String description = menuManagementPanel.getDescriptionField().getText();
        double price = Double.parseDouble(menuManagementPanel.getPriceField().getText());
        List<String> ingredients = List.of(menuManagementPanel.getIngredientsField().getText().split(";"));

        // Create updated MenuItem and edit it in the menu service
        MenuItem updatedItem = new MenuItem(name, description, price, ingredients);
        menuService.editMenuItem(updatedItem);

        // Update GUI
        JOptionPane.showMessageDialog(menuManagementPanel, "Menu item updated successfully!");
    }

    private void viewAllMenuItems() {
        // Retrieve all menu items from the menu service
        List<MenuItem> menuItems = menuService.getAllMenuItems();

        // Display the menu items in the GUI (this could be in a text area, table, etc.)
        StringBuilder itemsDisplay = new StringBuilder();
        for (MenuItem item : menuItems) {
            itemsDisplay.append(item.getName()).append(" - ")
                    .append(item.getDescription()).append(" - $")
                    .append(item.getPrice()).append("\nIngredients: ")
                    .append(String.join(", ", item.getIngredients())).append("\n\n");
        }

        menuManagementPanel.getMenuDisplayArea().setText(itemsDisplay.toString());
    }
}
