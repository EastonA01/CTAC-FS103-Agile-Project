package org.example.restaurant.gui;

/*
Interface for managing tables.
 */
import javax.swing.*;
import java.awt.*;

public class TableManagementPanel extends JPanel {
    private JTable tableStatusTable;
    private JButton assignTableButton;
    private JTextField tableIdField;
    private JComboBox<String> statusComboBox;

    public TableManagementPanel() {
        // Initialize components
        tableStatusTable = new JTable(); // This will be populated with table data later
        assignTableButton = new JButton("Assign Table");
        tableIdField = new JTextField(5);
        statusComboBox = new JComboBox<>(new String[]{"Available", "Reserved", "Occupied"});

        // Set up layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Table ID:"), gbc);

        gbc.gridx = 1;
        add(tableIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Status:"), gbc);

        gbc.gridx = 1;
        add(statusComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(assignTableButton, gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(new JScrollPane(tableStatusTable), gbc);
    }

    // Getters for components
    public JButton getAssignTableButton() {
        return assignTableButton;
    }

    public JTextField getTableIdField() {
        return tableIdField;
    }

    public JComboBox<String> getStatusComboBox() {
        return statusComboBox;
    }

    public JTable getTableStatusTable() {
        return tableStatusTable;
    }

    public void updateTableStatusTable(Object[][] data, String[] columnNames) {
        tableStatusTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
