package DomainLayer;

import DataAccessLayer.FileManipulator;
import Exceptions.IndexNotFoundException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
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
            System.out.println("Press 2 to remove a project");
            System.out.println("Press 3 to update a project");
            System.out.println("Press 4 to calculate project, activity, and task duration by hours");
            System.out.println("Press 5 to find number of distinct employees " +
                    "and consultants assigned to a project, activity, and task");
            System.out.println("Press 6 to save all projects and quit.");

            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();

            try {
                parseCommand(index);
            } catch (IndexNotFoundException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseCommand(int index) throws IndexNotFoundException, ParseException {
        if (index > 6 || index <= 0) {
            throw new IndexNotFoundException("Index is not valid, " +
                    "should be between 0 and 5.");
        }

        Scanner scanner = new Scanner(System.in);

        switch (index) {
            case 1:
                System.out.println("Please enter the name of the project:");
                String name = scanner.nextLine();
                System.out.println("Please enter the description of the project:");
                String description = scanner.nextLine();
                System.out.println("Please enter the start date of the project " +
                        "in this format yyyy-mm-dd:");
                String startDate = scanner.nextLine();
                DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                Date date = formatter.parse(startDate);
                projectList.add(new Project(name, description, date, new ArrayList<>()));
                break;
            case 2:
                System.out.println("Please enter the name of the project you would like to remove:");
                String name2 = scanner.nextLine();
                Project projectToBeRemoved = findProject(name2);
                if (projectToBeRemoved != null)
                    projectList.remove(projectToBeRemoved);
                else
                    System.out.println("The project you specified cannot be" +
                            "found");
                break;
            case 3:
                System.out.println("Please enter the name of the project you would like to update:");
                String name3 = scanner.nextLine();
                Project projectToBeUpdated = findProject(name3);
                if (projectToBeUpdated != null)
                    updateProject(projectToBeUpdated);
                else
                    System.out.println("The project you specified cannot be" +
                            "found");
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                writeProjectsToFile();
                System.exit(0);
                break;
            default:
                break;
        }
    }

    private void updateProject(Project projectToBeUpdated) throws ParseException {
        System.out.println("Press 1 to add, remove or update an activity.");
        System.out.println("Press 2 to add, remove or update a task.");
        System.out.println("Press 3 to add, remove or update a resource.");
        System.out.println("Press 4 to assign a resource to a task.");
        System.out.println("Press 5 to unassign a resource from a task.");

        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();

        switch(index) {
            case 1:
                updateActivities(projectToBeUpdated);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    private void updateActivities(Project projectToBeUpdated) throws ParseException {
        System.out.println("Press 1 to add an activity to corresponding project.");
        System.out.println("Press 2 to remove an activity from corresponding project.");
        System.out.println("Press 3 to update an activity from corresponding project");

        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();

        switch (index){
            case 1:
                System.out.println("Please enter a number for the activity " +
                        "you would like to add:");
                int number = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please enter the description of the " +
                        "activity you would like to add:");
                String description = scanner.nextLine();
                System.out.println("Please enter the start date of the activity " +
                        "in this format yyyy-mm-dd:");
                String startDate = scanner.nextLine();
                DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                Date date = formatter.parse(startDate);
                System.out.println("Please enter the deliverable of the " +
                        "activity you would like to add:");
                String deliverable = scanner.nextLine();
                projectToBeUpdated.addActivity(
                        new Activity(number,
                                description, date, deliverable, new ArrayList<>()));
                break;
            case 2:
                System.out.println("Please enter the number of the activity you would like to add:");
                int number2 = scanner.nextInt();
                projectToBeUpdated.removeActivity(number2);
            case 3:
                System.out.println("Please enter the number of the activity you would like to update:");
                int number3 = scanner.nextInt();
                System.out.println("Please enter the deliverable of the activity to update it:");
                String deliverable2 = scanner.nextLine();
                projectToBeUpdated.updateActivity(deliverable2,projectToBeUpdated.getActivity(number3));
        }

    }

    public void addProject(String name, String description, Date startDate) {
        Project project = new Project(name, description, startDate,
                new ArrayList<>());
        if (!projectList.contains(project))
            projectList.add(new Project(name, description, startDate,
                    new ArrayList<>()));
    }

    private Project findProject(String name) {
        for (Project project : projectList) {
            if (project.getName().equals(name)) {
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

    private void writeProjectsToFile() {
        if (projectList.size() > 0)
            manipulator.writeToFile(new Gson().toJson(projectList, ArrayList.class));
    }

    private void readProjectsFromFile() {
        try {
            if (manipulator.readLastFile() != null) {
                Gson gson = new GsonBuilder()
                        .setDateFormat("MMMd,yyyyHH:mm:ssa")
                        .create();
                projectList = gson.fromJson(manipulator.readLastFile(),
                        new TypeToken<ArrayList<Project>>(){}.getType());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
