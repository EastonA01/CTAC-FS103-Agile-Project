package org.example.restaurant.service;

import org.example.restaurant.model.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private List<MenuItem> menuItems;
    private static final String MENU_CSV_FILE = "src/main/resources/org/example/restaurant/menuItems.csv";

    public MenuService() {
        // Initialize menu items list (maybe load from file)
        menuItems = new ArrayList<>();
        loadMenuItemsFromCSV(); // Load existing menu items from a CSV file
    }

    public void addMenuItem(MenuItem item) {
        // Add a new menu item
        menuItems.add(item);
        saveMenuItemsToCSV(); // Save the updated menu to a CSV file
    }

    public void removeMenuItem(String itemName) {
        // Remove a menu item by name
        menuItems.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
        saveMenuItemsToCSV(); // Save the updated menu to a CSV file
    }

    public void editMenuItem(MenuItem updatedItem) {
        // Edit an existing menu item
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            if (item.getName().equalsIgnoreCase(updatedItem.getName())) {
                menuItems.set(i, updatedItem);
                break;
            }
        }
        saveMenuItemsToCSV(); // Save the updated menu to a CSV file
    }

    public List<MenuItem> getAllMenuItems() {
        // Return all menu items
        return new ArrayList<>(menuItems); // Return a copy of the list
    }

    // Implement methods to load and save menu items to file
    private void saveMenuItemsToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MENU_CSV_FILE))) {
            for (MenuItem item : menuItems) {
                bw.write(item.getName() + "," + item.getDescription() + "," + item.getPrice() + "," +
                        String.join(";", item.getIngredients()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Replace with logging if preferred
        }
    }

    private void loadMenuItemsFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(MENU_CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", 4);
                String name = values[0];
                String description = values[1];
                double price = Double.parseDouble(values[2]);
                List<String> ingredients = List.of(values[3].split(";"));
                menuItems.add(new MenuItem(name, description, price, ingredients));
            }
        } catch (IOException e) {
            e.printStackTrace(); // Replace with logging if preferred
        }
    }
}

