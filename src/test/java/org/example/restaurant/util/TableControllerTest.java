import org.example.restaurant.controller.TableController;
import org.example.restaurant.gui.TableManagementPanel;
import org.example.restaurant.model.Table;
import org.example.restaurant.service.TableService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TableControllerTest {

    private TableService tableService;
    private TableManagementPanel tableManagementPanel;
    private TableController tableController;

    @Before
    public void setUp() {
        tableService = mock(TableService.class);
        tableManagementPanel = mock(TableManagementPanel.class);
        tableController = new TableController(tableService, tableManagementPanel);
    }

    @Test
    public void testSaveTable_ShouldAddTableWhenIdNotInUse() {
        when(tableManagementPanel.getTableIdField().getText()).thenReturn("1");
        when(tableManagementPanel.getTableNameField().getText()).thenReturn("Table 1");
        when(tableManagementPanel.getOccupantsField().getText()).thenReturn("4");
        when(tableManagementPanel.getStatusComboBox().getSelectedItem()).thenReturn("Occupied");

        when(tableService.getAllTables()).thenReturn(new java.util.ArrayList<>());

        tableController.saveTable();

        verify(tableService, times(1)).addTable(any(Table.class));
    }

    @Test
    public void testSaveTable_ShouldShowErrorWhenIdInUse() {
        when(tableManagementPanel.getTableIdField().getText()).thenReturn("1");
        when(tableService.getAllTables()).thenReturn(java.util.Collections.singletonList(new Table(1, "Existing Table", 4, "Occupied", 0.0)));

        tableController.saveTable();

        verify(tableService, times(0)).addTable(any(Table.class));
        verify(tableManagementPanel, times(1)).showMessageDialog("Table ID 1 is already in use.");
    }

    @Test
    public void testDeleteTable_ShouldRemoveTableWhenSelected() {
        when(tableManagementPanel.getTable().getSelectedRow()).thenReturn(0);
        when(tableManagementPanel.getTable().getValueAt(0, 0)).thenReturn(1);

        tableController.deleteSelectedTable();

        verify(tableService, times(1)).deleteTable(1);
    }
}
