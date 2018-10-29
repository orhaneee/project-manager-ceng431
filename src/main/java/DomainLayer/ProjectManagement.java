package DomainLayer;

import DataAccessLayer.FileManipulator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.net.PortUnreachableException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProjectManagement {

    private List<Project> projectList = new ArrayList<>();
    private List<Resource> resourceList = new ArrayList<>();

    private static ProjectManagement projectManager = new ProjectManagement();

    private ProjectManagement() {}

    public static ProjectManagement getInstance() {
        return projectManager;
    }

    private FileManipulator manipulator = new FileManipulator();

    public void updateProject(String name) throws ParseException {
        Project projectToBeUpdated = findProject(name);
        System.out.println("Press 1 to add, remove or update an activity.");
        System.out.println("Press 2 to add, remove or update a task.");
        System.out.println("Press 3 to add, remove or update a resource.");
        System.out.println("Press 4 to assign a resource to a task.");
        System.out.println("Press 5 to unassign a resource from a task.");

        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();

        switch(index) {
            case 1:
                manipulateActivities(projectToBeUpdated);
                break;
            case 2:
                System.out.println("Please enter the id of the activity to manipulate its tasks in " + name + " project:");
                int number = scanner.nextInt();
                scanner.nextLine();
                Activity activityToBeUpdated = projectToBeUpdated.findActivity(number);
                manipulateTasks(activityToBeUpdated);
                break;
            case 3:
                manipulateResources(projectToBeUpdated);
                break;
            case 4:
                System.out.println("Please first, enter the id of the resource that will be assigned to a task:");
                int resourceId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please enter the id of the task:");
                int taskId = scanner.nextInt();
                scanner.nextLine();
                assignResourceToTask(name, resourceId, taskId);
                break;
            case 5:
                System.out.println("Please first, enter the id of the resource that will be unassigned from a task:");
                int resourceId2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please enter the id of the task:");
                int taskId2 = scanner.nextInt();
                scanner.nextLine();
                unassignResourceFromTask(name, resourceId2, taskId2);
                break;
            default:
                break;
        }
    }


    private void manipulateActivities(Project projectToBeUpdated) throws ParseException {
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
                System.out.println("Please enter the number of the activity you would like to delete:");
                int number2 = scanner.nextInt();
                scanner.nextLine();
                projectToBeUpdated.removeActivity(number2);
                break;
            case 3:
                System.out.println("Please enter the number of the activity you would like to update:");
                int number3 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please enter the deliverable of the activity to update it:");
                String deliverable2 = scanner.nextLine();
                projectToBeUpdated.updateActivity(deliverable2,projectToBeUpdated.findActivity(number3));
                break;
            default:
                break;
        }

    }

    private void manipulateTasks(Activity activityToBeUpdated) throws ParseException {
        System.out.println("Press 1 to add a task to corresponding activity.");
        System.out.println("Press 2 to remove a task from corresponding activity.");
        System.out.println("Press 3 to update a task from corresponding activity.");

        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();

        switch (index){
            case 1:
                System.out.println("Please enter a number for the task " +
                        "you would like to add:");
                int number = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please enter the description of the " +
                        "task you would like to add:");
                String description = scanner.nextLine();
                System.out.println("Please enter the start date of the task " +
                        "in this format yyyy-mm-dd:");
                String startDate = scanner.nextLine();
                DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                Date date = formatter.parse(startDate);
                System.out.println("Please enter the hour of the " +
                        "task you would like to add:");
                int hour = scanner.nextInt();
                scanner.nextLine();
                activityToBeUpdated.addTask(new Task(number, description, date, hour)); //adds the task to activity
                break;
            case 2:
                System.out.println("Please enter the id of the task you would like to delete:");
                int number2 = scanner.nextInt();
                scanner.nextLine();
                activityToBeUpdated.removeTask(number2); //removes from taskList
                removeTaskFromResource(number2); //removes from resource's taskList
                break;
            case 3:
                System.out.println("Please enter the number of the task you would like to update:");
                int number3 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please enter 1 for updating its hour or enter 2 for updating its resource id:");
                int number4 = scanner.nextInt();
                scanner.nextLine();
                if (number4 == 1) {
                    System.out.println("Please enter the new hour for this task:");
                    int hour2 = scanner.nextInt();
                    scanner.nextLine();
                    activityToBeUpdated.findTask(number3).setHours(hour2);
                }
                else if (number4 == 2) {
                    System.out.println("Please enter the id of its new resource:");
                    int resourceId2 = scanner.nextInt();
                    scanner.nextLine();
                    activityToBeUpdated.findTask(number3).setResourceId(resourceId2);
                    resourceList.get(resourceList.indexOf(findResource(resourceId2))).addTask(number3);
                } else {
                    System.out.println("You didn't enter 1 or 2.");
                }
                break;
            default:
                break;
        }
    }

    private void manipulateResources (Project projectToBeUpdated) {
        System.out.println("Press 1 to add a resource to corresponding project, and to Resource List.");
        System.out.println("Press 2 to remove a resource from all projects, and from Resource List.");
        System.out.println("Press 3 to update a resource from corresponding project.");

        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        scanner.nextLine();

        switch (index){
            case 1:
                System.out.println("Please enter the id of the new resource " +
                        "you would like to add:");
                int number = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please enter 1 if this resource is an employee, or enter 2 if this resource is a consultant.");
                int number4 = scanner.nextInt();
                scanner.nextLine();
                if (number4 == 1) {
                    resourceList.add(new Employee(number));
                } else if (number4 == 2) {
                    resourceList.add(new Consultant(number));
                }
                break;
            case 2:
                System.out.println("Please enter the id of the resource you would like to delete:");
                int resourceId = scanner.nextInt();
                scanner.nextLine();
                removeResourceFromAllProject(resourceId);
                removeResourceFromResourceList(resourceId);
                break;
            case 3:
                System.out.println("Please enter the number of the activity you would like to update:");
                int number6 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Please enter the deliverable of the activity to update it:");
                String deliverable2 = scanner.nextLine();
                projectToBeUpdated.updateActivity(deliverable2,projectToBeUpdated.findActivity(number6));
                break;
            default:
                break;
        }
    }

    public void calculateTimes() {
        System.out.println("Please press 1 to see the duration of projects.");
        System.out.println("Please press 2 to see the duration of activities of a project");
        System.out.println("Please press 3 to see the duration of tasks of a activity of a project");

        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        scanner.nextLine();
        switch (number){
            case 1:
                printAllProjectsDuration();
                break;
            case 2:
                System.out.println("Please enter the name of the project that you would like to see its activities duration.");
                String projectName = scanner.nextLine();
                printAllActivitiesOfProjectDuration(projectName);
                break;
            case 3:
                System.out.println("Please enter the name of the project that you would like to see its tasks duration.");
                String projectName2 = scanner.nextLine();
                System.out.println("Please enter the id of the activity of " + projectName2 + " project to its tasks duration.");
                int activityId = scanner.nextInt();
                scanner.nextLine();
                printAllTasksOfActivityOfProjectDuration(projectName2, activityId);
                break;
            default:
                break;


        }
    }


    //should print resource list.
    public void printResourceList(){
        for (Resource resource : resourceList) {
            System.out.println(resource.getId() + "   " + resource.getTaskList().toString());
        }
    }


    // should print all projects and its details.
    public void printProjectList() {
        for (Project project : projectList) {
            System.out.println(project.getName() + "    " + project.getDescription() + "    " + project.getStartDate().toString());
            System.out.println(project.getActivityList());
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


    public void removeProject(String name) {
        Project project = findProject(name);
        projectList.remove(project);
    }


    public void writeProjectsToFile() {
        if (projectList.size() > 0)
            manipulator.writeToFile(new Gson().toJson(projectList, ArrayList.class));
    }

    public void readProjectsFromFile() {
        try {
            if (manipulator.readLastFile() != null) {
                Gson gson = new GsonBuilder()
                        .setDateFormat("MMMdd,yyyy,HH:mm:ssa")
                        .create();
                projectList = gson.fromJson(manipulator.readLastFile(),
                        new TypeToken<ArrayList<Project>>(){}.getType());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Resource findResource (int resourceId) {
        for (Resource resource : resourceList) {
            if (resource.getId() == resourceId) {
                return resource;
            }
        }
        return null;
    }

    private void removeTaskFromResource (int taskId) {
        for (Resource resource : resourceList) {
            if (resource.getTaskList().contains(taskId)) {
                resource.removeTask(taskId);
            }
        }
    }

    private void removeResourceFromAllProject (int resourceId) {
        for (Project project : projectList) {
            project.removeResourceFromAllActivities(resourceId);
        }
    }

    private void removeResourceFromResourceList(int resourceId) {
        for (Resource resource : resourceList) {
            if (resourceId == resource.getId()) {
                resourceList.remove(resource);
            }
        }
    }

    private boolean isResourceExist(int resourceId) {
        for (Resource resource : resourceList) {
            if (resource.getId() == resourceId){
                return true;
            }
        }
        return false;
    }

    private void assignResourceToTask(String projectName, int resourceId, int taskId) {
        if (isResourceExist(resourceId)) {
            projectList.get(projectList.indexOf(findProject(projectName))).findActivityFromTaskId(taskId).findTask(taskId).setResourceId(resourceId);
            findResource(resourceId).addTask(taskId);
        }

    }

    private void unassignResourceFromTask(String projectName, int resourceId, int taskId) {
        if (projectList.get(projectList.indexOf(findProject(projectName))).findActivityFromTaskId(taskId).findTask(taskId).getResourceId() == resourceId){
            projectList.get(projectList.indexOf(findProject(projectName))).findActivityFromTaskId(taskId).findTask(taskId).setResourceId(0);
            findResource(resourceId).removeTask(taskId);
        }
    }

    private void printAllProjectsDuration() {
        for (Project project : projectList) {
            System.out.println("Project name: " + project.getName() + "    Duration: " + project.calculateAllActivityDuration());
        }
    }

    private void printAllActivitiesOfProjectDuration(String projectName) {
        findProject(projectName).printAllActivitiesOfDuration();
    }

    private void printAllTasksOfActivityOfProjectDuration(String projectName, int activityId) {
        findProject(projectName).findActivity(activityId).printAllTasksOfDuration();
    }



}