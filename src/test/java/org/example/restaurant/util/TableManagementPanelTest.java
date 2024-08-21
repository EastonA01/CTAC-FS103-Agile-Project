import org.example.restaurant.gui.TableManagementPanel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class TableManagementPanelTest {

    private TableManagementPanel panel;

    @Before
    public void setUp() {
        panel = new TableManagementPanel();
    }

    @Test
    public void testPanelInitialization() {
        assertNotNull(panel.getTableIdField());
        assertNotNull(panel.getTableNameField());
        assertNotNull(panel.getOccupantsField());
        assertNotNull(panel.getStatusComboBox());
        assertNotNull(panel.getSaveTableButton());
        assertNotNull(panel.getDeleteTableButton());
        assertNotNull(panel.getTable());
    }

    @Test
    public void testUpdateTableData() {
        Object[][] data = {
                {1, "Table 1", 4, "Occupied", 0.0},
                {2, "Table 2", 2, "Available", 0.0}
        };

        panel.updateTableData(data);

        JTable table = panel.getTable();
        assertEquals(2, table.getRowCount());
        assertEquals(5, table.getColumnCount());
    }
}
