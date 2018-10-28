package PresentationLayer;

import DomainLayer.ProjectManagement;

public class MenuApp {

    public static void main(String[] args) {
        Menu menu = Menu.getInstance();
        menu.init();
    }
}
