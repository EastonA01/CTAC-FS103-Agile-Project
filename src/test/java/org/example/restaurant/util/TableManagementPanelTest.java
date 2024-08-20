package org.example.restaurant.util;

import org.example.restaurant.gui.TableManagementPanel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class TableManagementPanelTest {

    private TableManagementPanel tableManagementPanel;

    @Before
    public void setUp() {
        tableManagementPanel = new TableManagementPanel();
    }

    @Test
    public void testComponentInitialization() {
        assertNotNull(tableManagementPanel.getAssignTableButton());
        assertNotNull(tableManagementPanel.getTableIdField());
        assertNotNull(tableManagementPanel.getStatusComboBox());
        assertNotNull(tableManagementPanel.getTableStatusTable());

        assertEquals(JComboBox.class, tableManagementPanel.getStatusComboBox().getClass());
        assertEquals(JButton.class, tableManagementPanel.getAssignTableButton().getClass());
        assertEquals(JTextField.class, tableManagementPanel.getTableIdField().getClass());
    }
}
