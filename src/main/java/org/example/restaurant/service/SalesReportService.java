package org.example.restaurant.service;

/*
Generates and exports sales reports.
 */

import org.example.restaurant.model.MenuItem;
import org.example.restaurant.model.Order;
import org.example.restaurant.model.Table;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalesReportService {
    private List<Order> orders;

    public SalesReportService(List<Order> orders) {
        this.orders = orders;
    }

    public String generateDailySalesReport(TableService tableService) {
        // Initialize the report string with a header
        StringBuilder report = new StringBuilder();
        report.append("-----------------------------\n");
        report.append("Daily Sales Report\n");

        // Add the current date
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        report.append("Date: ").append(date).append("\n");
        report.append("-----------------------------\n");

        // Calculate total revenue
        double totalRevenue = 0;
        for (Order order : orders) {
            totalRevenue += order.getTotalPrice();
        }
        report.append("Total Revenue: $").append(new DecimalFormat("#0.00").format(totalRevenue)).append("\n\n");

        // Get and display the most popular items
        report.append("Most Popular Items:\n");
        List<Map.Entry<String, Integer>> mostPopularItems = getTopThreeBestSellingItems(orders);
        for (int i = 0; i < mostPopularItems.size(); i++) {
            Map.Entry<String, Integer> entry = mostPopularItems.get(i);
            report.append(i + 1).append(". ").append(entry.getKey()).append(": ").append(entry.getValue()).append(" orders\n");
        }
        report.append("\n");

        // Calculate and display table sales
        report.append("Table Sales:\n");
        List<Table> tables = tableService.getAllTables();
        tables.sort((t1, t2) -> Double.compare(t2.getTotalSales(), t1.getTotalSales()));  // Sort tables by total sales TODO: implement getTotalSales
        for (int i = 0; i < Math.min(tables.size(), 3); i++) {  // Display top 3 tables
            Table table = tables.get(i);
            report.append(i + 1).append(". Table ").append(table.getTableId()).append(": $").append(new DecimalFormat("#0.00").format(table.getTotalSales())).append("\n");
        }
        report.append("\n");

        // Display detailed orders
        report.append("Detailed Orders:\n");
        for (Order order : orders) {
            report.append("Order ID: #").append(order.getOrderId()).append("\n");
            report.append("Table ID: ").append(order.getTableId()).append("\n"); // TODO: Implement getTableId
            report.append("Items:\n");
            for (MenuItem item : order.getItems()) {
                String itemName = item.getName();
                int quantity = entry.getValue(); // TODO: Somehow get the QUANTITY of item in order
                double itemPrice = item.getPrice() * quantity;  // Assuming getItemPrice() returns price per item
                report.append("  - ").append(itemName).append(": ").append(quantity).append(" ($").append(new DecimalFormat("#0.00").format(itemPrice)).append(")\n");
            }
            report.append("Total: $").append(new DecimalFormat("#0.00").format(order.getTotalPrice())).append("\n\n");
        }

        // Return the generated report
        return report.toString();
    }


    public List<Map.Entry<String, Integer>> getTopThreeBestSellingItems(List<Order> orders) {
        // Map to store item name and its total count
        Map<String, Integer> itemCountMap = new HashMap<>();

        // Iterate through each order
        for (Order order : orders) {
            // Iterate through each MenuItem in the order
            for (MenuItem item : order.getItems()) {
                String itemName = item.getName();

                // Update the item count in the map
                itemCountMap.put(itemName, itemCountMap.getOrDefault(itemName, 0) + 1);
            }
        }

        // Convert the map entries to a list and sort by count in descending order
        List<Map.Entry<String, Integer>> sortedItems = new ArrayList<>(itemCountMap.entrySet());
        sortedItems.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Return the top three items (or fewer if there are less than three)
        return sortedItems.subList(0, Math.min(3, sortedItems.size()));
    }

    public int getItemCount(Order order){
        // Map to store item name and its total count
        Map<String, Integer> itemCountMap = new HashMap<>();
        for (MenuItem item : order.getItems()) {
            String itemName = item.getName();

            // Update the item count in the map
            itemCountMap.put(itemName, itemCountMap.getOrDefault(itemName, 0) + 1);
        }
    }

    public void exportReportToFile(String report, String filePath) {
        // TODO: Export the report to a text file
    }

    public static void main(String[] args) {
        SalesReportService salesReportService = new SalesReportService(new ArrayList<Order>());
        salesReportService.generateDailySalesReport(new TableService());
    }
}
