package org.example.restaurant.service;

import org.example.restaurant.model.InventoryItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private List<InventoryItem> inventoryItems;
    private static final String INVENTORY_CSV_FILE = "src/main/resources/org/example/restaurant/inventoryItems.csv";

    public InventoryService() {
        inventoryItems = new ArrayList<>();
        loadInventoryItemsFromCSV(); // Load existing inventory items from a CSV file
    }

    public void updateInventoryItem(String ingredientName, int quantity) {
        for (InventoryItem item : inventoryItems) {
            if (item.getIngredientName().equalsIgnoreCase(ingredientName)) {
                item.setQuantity(quantity); // Update the quantity
                saveInventoryItemsToCSV(); // Save the updated inventory to a CSV file
                return;
            }
        }
        // If the item does not exist, add it
        inventoryItems.add(new InventoryItem(ingredientName, quantity));
        saveInventoryItemsToCSV(); // Save the updated inventory to a CSV file
    }

    public List<InventoryItem> getAllInventoryItems() {
        return new ArrayList<>(inventoryItems); // Return a copy of the list
    }

    public boolean isRunningLow(String ingredientName) {
        for (InventoryItem item : inventoryItems) {
            if (item.getIngredientName().equalsIgnoreCase(ingredientName)) {
                return item.getQuantity() < 5; // Assuming a threshold of 5 units as "running low"
            }
        }
        return false;
    }

    private void saveInventoryItemsToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(INVENTORY_CSV_FILE))) {
            for (InventoryItem item : inventoryItems) {
                bw.write(item.getIngredientName() + "," + item.getQuantity());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Replace with logging if preferred
        }
    }

    private void loadInventoryItemsFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(INVENTORY_CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String ingredientName = values[0];
                int quantity = Integer.parseInt(values[1]);
                inventoryItems.add(new InventoryItem(ingredientName, quantity));
            }
        } catch (IOException e) {
            e.printStackTrace(); // Replace with logging if preferred
        }
    }
}



