package org.example.restaurant.gui;


/*
Main application window, includes menus to navigate between different functionalities.
 */

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    // Components like menus, panels, etc.
    private JMenuBar menuBar;
    private JPanel contentPanel;

    private JPanel mainPanel;
    private JPanel greetingPanel;
    private JLabel greetingLabel;
    private JButton inventoryButton;
    private JButton ringInOrdersButton;
    private JButton salesReportsButton;
    private JButton addTableButton;
    private JButton removeTableButton;


    public MainFrame(String username) {
        // Create a new panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create a greeting panel
        greetingPanel = new JPanel();
        greetingPanel.setLayout(new FlowLayout());

        // Create a greeting label
        greetingLabel = new JLabel("Welcome to Restaurant Management System, " + username + "!");
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Add the greeting label to the greeting panel
        greetingPanel.add(greetingLabel);

        // Add the greeting panel to the main panel
        mainPanel.add(greetingPanel, BorderLayout.NORTH);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));

        // Create buttons
        inventoryButton = new JButton("Inventory Management");
        ringInOrdersButton = new JButton("Ring in Orders");
        salesReportsButton = new JButton("View Sales Reports");
        addTableButton = new JButton("Add Table");
        removeTableButton = new JButton("Remove Table");

        // Add buttons to the button panel
        buttonPanel.add(inventoryButton);
        buttonPanel.add(ringInOrdersButton);
        buttonPanel.add(salesReportsButton);
        buttonPanel.add(addTableButton);
        buttonPanel.add(removeTableButton);

        // Add the button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add panel to the frame
        add(mainPanel);

        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Get the insets (size of UI elements)
        Insets insets = getInsets();

        // Set the size of the frame to the screen size minus the insets
        setSize(screenSize.width, screenSize.height - insets.top - insets.bottom);
        pack(); // Update the frame size

        // Set the frame to be full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // TODO: Initialize components
        // TODO: Add components to frame
        // TODO: Set up menus and their action listeners
        // TODO: Set frame properties (size, layout, etc.)

    // TODO: Implement methods to switch between different panels
}
