package PresentationLayer;

import DomainLayer.ProjectManagement;
import Exceptions.IndexNotFoundException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu {
    private static Menu ourInstance = new Menu();

    public static Menu getInstance() {
        return ourInstance;
    }

    private Menu() {
    }

    ProjectManagement projectManager = ProjectManagement.getInstance();

    public void init() {
        projectManager.readProjectsFromFile();

        while (true) {
            System.out.println("Welcome to Project Management System!");
            System.out.println("Press 1 to add a new project");
            System.out.println("Press 2 to remove a project");
            System.out.println("Press 3 to update a project");
            System.out.println("Press 4 to calculate project, activity, and task duration by hours");
            System.out.println("Press 5 to find number of distinct employees " +
                    "and consultants assigned to a project, activity, and task");
            System.out.println("Press 6 to display project details.");
            System.out.println("Press 7 to display resource details.");
            System.out.println("Press 8 to save all projects and quit.");

            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();

            try {
                parseCommand(index);
            } catch (IndexNotFoundException | ParseException e) {
                e.printStackTrace();
            }
            try {
                executeCommand(index);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


    }

    private void parseCommand(int index) throws IndexNotFoundException, ParseException {
        if (index > 8 || index <= 0) {
            throw new IndexNotFoundException("Index is not valid, " +
                    "should be between 1 and 8.");
        }
    }

    private void executeCommand(int index) throws ParseException {
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
                projectManager.addProject(name, description, date);
                break;
            case 2:
                System.out.println("Please enter the name of the project you would like to remove:");
                String name2 = scanner.nextLine();
                projectManager.removeProject(name2);
                break;
            case 3:
                System.out.println("Please enter the name of the project you would like to update:");
                String name3 = scanner.nextLine();
                projectManager.updateProject(name3);
                break;
            case 4: //calculate project, activity, and task duration by hours
                break;
            case 5: //find number of distinct employees and consultants assigned to a project, activity and task.
                break;
            case 6: projectManager.printProjectList(); //display project details.
                break;
            case 7: System.out.println(projectManager.getResourceList().toString()); //display resource details.
                break;
            case 8:
                projectManager.writeProjectsToFile();
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
