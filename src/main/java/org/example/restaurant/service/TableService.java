package org.example.restaurant.service;

import org.example.restaurant.model.Table;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TableService {
    private List<Table> tables;
    private static final String TABLE_CSV_FILE = "src/main/resources/org/example/restaurant/tables.csv";

    public TableService() {
        tables = new ArrayList<>();
        loadTablesFromCSV();
    }

    public void addTable(Table table) {
        // Check if table with the same ID already exists
        for (Table existingTable : tables) {
            if (existingTable.getTableId() == table.getTableId()) {
                // Update existing table
                existingTable.setTableName(table.getTableName());
                existingTable.setOccupants(table.getOccupants());
                existingTable.setStatus(table.getStatus());
                existingTable.setTotalPrice(table.getTotalPrice());
                saveTablesToCSV();  // Save updated list
                return;
            }
        }
        // If no matching ID, add a new table
        tables.add(table);
        saveTablesToCSV();  // Save updated list
    }

    public void deleteTable(int tableId) {
        tables.removeIf(table -> table.getTableId() == tableId);
        saveTablesToCSV();  // Save updated list
    }

    public void deleteTableByName(String tableName) {
        tables.removeIf(table -> table.getTableName().equalsIgnoreCase(tableName));
        saveTablesToCSV();  // Save updated list
    }

    public List<Table> getAllTables() {
        return new ArrayList<>(tables);  // Return a copy of the list
    }

    public Table getTableById(int tableId) {
        return tables.stream()
                .filter(table -> table.getTableId() == tableId)
                .findFirst()
                .orElse(null);
    }

    private void saveTablesToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TABLE_CSV_FILE))) {
            for (Table table : tables) {
                bw.write(table.getTableId() + "," + table.getTableName() + "," +
                        table.getOccupants() + "," + table.getStatus() + "," +
                        table.getTotalPrice());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();  // Replace with logging if preferred
        }
    }

    private void loadTablesFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(TABLE_CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int tableId = Integer.parseInt(values[0]);
                String tableName = values[1];
                int occupants = Integer.parseInt(values[2]);
                String status = values[3];
                double totalPrice = Double.parseDouble(values[4]);
                tables.add(new Table(tableId, tableName, occupants, status));
            }
        } catch (IOException e) {
            e.printStackTrace();  // Replace with logging if preferred
        }
    }
}
