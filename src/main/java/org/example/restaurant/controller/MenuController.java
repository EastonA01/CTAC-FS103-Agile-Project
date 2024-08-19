package org.example.restaurant.controller;

/*
Connects the GUI with the MenuService.
 */

import org.example.restaurant.service.MenuService;
import org.example.restaurant.gui.MenuManagementPanel;

public class MenuController {
    private MenuService menuService;
    private MenuManagementPanel menuManagementPanel;

    public MenuController(MenuService menuService, MenuManagementPanel menuManagementPanel) {
        this.menuService = menuService;
        this.menuManagementPanel = menuManagementPanel;

        // TODO: Set up action listeners for menu management panel
    }

    // TODO: Implement methods to interact with MenuService
}
