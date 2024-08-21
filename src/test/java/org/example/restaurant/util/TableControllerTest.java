package org.example.restaurant.util;

import org.example.restaurant.controller.TableController;
import org.example.restaurant.gui.TableManagementPanel;
import org.example.restaurant.model.Table;
import org.example.restaurant.service.TableService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class TableControllerTest {

    private TableService tableService;
    private TableManagementPanel tableManagementPanel;
    private TableController tableController;

    @Before
    public void setUp() {
        // Mock the TableService to avoid file I/O
        tableService = Mockito.mock(TableService.class);

        // Provide some default behavior for the mock
        List<Table> mockTables = new ArrayList<>();
        mockTables.add(new Table(1, 4, "Available"));
        mockTables.add(new Table(2, 2, "Occupied"));

        when(tableService.getAllTables()).thenReturn(mockTables);

        // Set up the actual panel and controller
        tableManagementPanel = new TableManagementPanel();
        tableController = new TableController(tableService, tableManagementPanel);
    }

    @Test
    public void testAssignTableButtonAction() {
        // Set up the GUI inputs
        tableManagementPanel.getTableIdField().setText("1");
        tableManagementPanel.getStatusComboBox().setSelectedItem("Reserved");

        // Simulate button click
        JButton assignButton = tableManagementPanel.getAssignTableButton();
        for (ActionListener al : assignButton.getActionListeners()) {
            al.actionPerformed(null);
        }

        // Capture the argument passed to updateTableStatus to verify the status change
        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> statusCaptor = ArgumentCaptor.forClass(String.class);

        verify(tableService).updateTableStatus(idCaptor.capture(), statusCaptor.capture());

        // Check that the correct table ID and status were passed
        assertEquals(Integer.valueOf(1), idCaptor.getValue());
        assertEquals("Reserved", statusCaptor.getValue());
    }
}
