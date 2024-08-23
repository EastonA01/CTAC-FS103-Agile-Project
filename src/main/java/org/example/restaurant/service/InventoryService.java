package org.example.restaurant.service;

import org.example.restaurant.model.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private List<InventoryItem> inventory;

    public InventoryService() {
        inventory = new ArrayList<>();
    }

    public void restockItem(String ingredientName, int quantity) {
        for (InventoryItem item : inventory) {
            if (item.getIngredientName().equalsIgnoreCase(ingredientName)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        inventory.add(new InventoryItem(ingredientName, quantity));
    }

    public InventoryItem getItemByName(String ingredientName) {
        for (InventoryItem item : inventory) {
            if (item.getIngredientName().equalsIgnoreCase(ingredientName)) {
                return item;
            }
        }
        return null;
    }

    public List<InventoryItem> getAllItems() {
        return new ArrayList<>(inventory);
    }

    public Object[][] getInventoryData() {
        Object[][] data = new Object[inventory.size()][2];
        for (int i = 0; i < inventory.size(); i++) {
            InventoryItem item = inventory.get(i);
            data[i][0] = item.getIngredientName();
            data[i][1] = item.getQuantity();
        }
        return data;
    }

    public void addIngredientsToInventory(List<String> ingredients) {
        for (String ingredient : ingredients) {
            restockItem(ingredient, 1);  // Increment quantity by 1 for each ingredient added to inventory
        }
    }
}







