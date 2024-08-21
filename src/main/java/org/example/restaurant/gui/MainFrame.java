package org.example.restaurant.gui;

import org.example.restaurant.controller.InventoryController;
import org.example.restaurant.controller.OrderController;
import org.example.restaurant.service.InventoryService;
import org.example.restaurant.service.OrderService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
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

    private InventoryManagementPanel inventoryManagementPanel;
    private OrderProcessingPanel orderProcessingPanel;
    private JMenu backMenu;  // Changed from JButton to JMenu

    public MainFrame(String username) {
        // Initialize components
        initializeComponents(username);

        // Set up menus and their action listeners
        setupMenu();

        // Add components to frame
        setupFrame();

        // Set up inventory management
        setupInventoryManagement();

        // Set up order processing
        setupOrderProcessing();
    }

    // Initialize components
    private void initializeComponents(String username) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        greetingPanel = new JPanel();
        greetingPanel.setLayout(new FlowLayout());

        greetingLabel = new JLabel("Welcome to Restaurant Management System, " + username + "!");
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        greetingPanel.add(greetingLabel);

        mainPanel.add(greetingPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));

        inventoryButton = new JButton("Inventory Management");
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("Inventory");
            }
        });

        ringInOrdersButton = new JButton("Ring in Orders");
        ringInOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("Orders");
            }
        });

        salesReportsButton = new JButton("View Sales Reports");
        addTableButton = new JButton("Add Table");
        removeTableButton = new JButton("Remove Table");

        buttonPanel.add(inventoryButton);
        buttonPanel.add(ringInOrdersButton);
        buttonPanel.add(salesReportsButton);
        buttonPanel.add(addTableButton);
        buttonPanel.add(removeTableButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(mainPanel, "Main");

        add(contentPanel);
    }

    // Set up inventory management
    private void setupInventoryManagement() {
        InventoryService inventoryService = new InventoryService();
        inventoryManagementPanel = new InventoryManagementPanel();
        InventoryController inventoryController = new InventoryController(inventoryService, inventoryManagementPanel);

        addPanel(inventoryManagementPanel, "Inventory");
    }

    // Set up order processing
    private void setupOrderProcessing() {
        OrderService orderService = new OrderService();
        orderProcessingPanel = new OrderProcessingPanel();
        OrderController orderController = new OrderController(orderService, orderProcessingPanel);

        addPanel(orderProcessingPanel, "Orders");
    }

    // Set up menus and their action listeners
    private void setupMenu() {
        menuBar = new JMenuBar();

        JMenu menu = new JMenu("Options");
        JMenuItem inventoryMenuItem = new JMenuItem("Inventory Management");
        JMenuItem orderMenuItem = new JMenuItem("Ring in Orders");
        JMenuItem reportsMenuItem = new JMenuItem("View Sales Reports");
        JMenuItem addTableMenuItem = new JMenuItem("Add Table");
        JMenuItem removeTableMenuItem = new JMenuItem("Remove Table");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        inventoryMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("Inventory");
            }
        });

        orderMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("Orders");
            }
        });

        reportsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("Reports");
            }
        });

        addTableMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("AddTable");
            }
        });

        removeTableMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("RemoveTable");
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(inventoryMenuItem);
        menu.add(orderMenuItem);
        menu.add(reportsMenuItem);
        menu.add(addTableMenuItem);
        menu.add(removeTableMenuItem);
        menu.addSeparator();
        menu.add(exitMenuItem);

        menuBar.add(menu);

        // Add the back button as a JMenu to match the style of the Options menu
        backMenu = new JMenu("Back");
        backMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showPanel("Main");
            }
        });

        menuBar.add(Box.createHorizontalGlue()); // Pushes the back button to the right side
        menuBar.add(backMenu);

        setJMenuBar(menuBar);
    }

    // Set frame properties
    private void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Implement methods to switch between different panels
    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, panelName);
    }

    public void addPanel(JPanel panel, String panelName) {
        contentPanel.add(panel, panelName);
    }
}



