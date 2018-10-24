package PresentationLayer;

import DomainLayer.ProjectManagementSystem;
import Exceptions.IndexNotFoundException;

import java.util.Scanner;

public class Menu {

    private ProjectManagementSystem pms = ProjectManagementSystem.getInstance();
    private static Menu menu = new Menu();

    private Menu() {}

    public static Menu getInstance() {
        return menu;
    }

    public void init() {


        System.out.println("Welcome to Project Management System!");
        System.out.println("Press 1 to add a new project");
        System.out.println("Press 2 to find and remove a project");
        System.out.println("Press 3 to find and update a project");
        System.out.println("Press 4 to calculate project, activity, and task duration by hours");
        System.out.println("Press 5 to find number of distinct employees and consultants assigned to a project, activity, and task");

        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();

        try {
            parseCommand(index);
        }
        catch (IndexNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void parseCommand(int index) throws IndexNotFoundException {
        if (index > 5 || index <= 0) {
            throw new IndexNotFoundException("Index is not valid.");
        }


    }
}
