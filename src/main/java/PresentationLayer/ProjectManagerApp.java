package PresentationLayer;

import DomainLayer.ProjectManagementSystem;

public class ProjectManagerApp {

    public static void main(String[] args) {
        ProjectManagementSystem pms = ProjectManagementSystem.getInstance();
        pms.init();
    }
}
