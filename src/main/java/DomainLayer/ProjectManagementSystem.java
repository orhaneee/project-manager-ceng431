package DomainLayer;

import DataAccessLayer.FileManipulator;
import Exceptions.IndexNotFoundException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProjectManagementSystem {

    private List<Project> projectList = new ArrayList<>();

    private static ProjectManagementSystem pms = new ProjectManagementSystem();

    private ProjectManagementSystem() {}

    public static ProjectManagementSystem getInstance() {
        return pms;
    }

    private FileManipulator manipulator;

    public void init() {
        manipulator = new FileManipulator();
        readProjectsFromFile();

        while (true) {
            System.out.println("Welcome to Project Management System!");
            System.out.println("Press 1 to add a new project");
            System.out.println("Press 2 to find and remove a project");
            System.out.println("Press 3 to find and update a project");
            System.out.println("Press 4 to calculate project, activity, and task duration by hours");
            System.out.println("Press 5 to find number of distinct employees " +
                    "and consultants assigned to a project, activity, and task");
            System.out.println("Press 6 to save all projects and quit.");

            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();

            try {
                parseCommand(index);
            } catch (IndexNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseCommand(int index) throws IndexNotFoundException {
        if (index > 5 || index <= 0) {
            throw new IndexNotFoundException("Index is not valid, " +
                    "should be between 0 and 5.");
        }

        switch (index) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                writeProjectsToFile();
                break;
            default:
                break;
        }

    }

    public void addProject(String name, String description, Date startDate) {
        Project project = new Project(name, description, startDate);
        if (!projectList.contains(project))
            projectList.add(new Project(name, description, startDate));
    }

    public Project findProject(String name) {
        for (Project project : projectList) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        return null;
    }

    public Project findProject(Date startDate) {
        for (Project project : projectList) {
            if (project.getStartDate().equals(startDate)) {
                return project;
            }
        }
        return null;
    }

    public void removeProject(Project project) {
        projectList.remove(project);
    }

    public void addActivity(Project project, Activity activity) {
        project.addActivity(activity);
    }

    public void removeActivity(Project project, Activity activity) {
        project.removeActivity(activity);
    }

    public void writeProjectsToFile() {
        manipulator.writeToFile(new Gson().toJson(projectList, ArrayList.class));
    }

    public void readProjectsFromFile() {
        try {
            if (manipulator.readLastFile() != null) {
                projectList = new Gson().fromJson(manipulator.readLastFile(),
                        new TypeToken<List<Project>>(){}.getType());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
