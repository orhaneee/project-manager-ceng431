package PresentationLayer;

import DomainLayer.ProjectManagementSystem;
import Exceptions.IndexNotFoundException;
import org.jetbrains.annotations.Contract;

import java.util.Scanner;

class Menu {

    private ProjectManagementSystem pms = ProjectManagementSystem.getInstance();
    private static Menu menu = new Menu();

    private Menu() {}

    @Contract(pure = true)
    static Menu getInstance() {
        return menu;
    }

    void init() {
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
        }
        catch (IndexNotFoundException e) {
            e.printStackTrace();
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
                break;
            default:
                break;
        }

    }
}
