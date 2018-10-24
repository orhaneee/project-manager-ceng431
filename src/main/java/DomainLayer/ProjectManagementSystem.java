package DomainLayer;

import java.util.Date;

public class ProjectManagementSystem {

    private static ProjectManagementSystem pms = new ProjectManagementSystem();

    private ProjectManagementSystem() {}

    public static ProjectManagementSystem getInstance() {
        return pms;
    }

    public void addProject(String name, String description, Date startDate){

    }

}
