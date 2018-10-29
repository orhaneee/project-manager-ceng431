package DomainLayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity {

    public Activity(int number, String description, Date startDate,
                    String deliverable, List<Task> taskList) {
        this.number = number;
        this.description = description;
        this.startDate = startDate;
        this.deliverable = deliverable;
        this.taskList = taskList;
    }

    private int number;

    private String description;

    private Date startDate;

    private String deliverable;

    private List<Task> taskList;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setDeliverable(String deliverable) {
        this.deliverable = deliverable;
    }

    public String getDeliverable() {
        return deliverable;
    }

    public int getHours() {
        if (taskList.size() == 0) {
            return 0;
        }
        else {
            int totalHour = 0;
            for (Task task : taskList) {
                totalHour += task.getHours();
            }
            return totalHour;
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task){
        taskList.remove(task);
    }

    public void removeTask(int taskId) {
        Task task = findTask(taskId);
        taskList.remove(task);
    }

    public Task findTask(int taskId) {
        for (Task task : taskList) {
            if (task.getNumber() == taskId) {
                return task;
            }
        }
        return null;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void updateTask(Task task, int number){
        if (taskList.contains(task)) {
            taskList.get(taskList.indexOf(task)).setNumber(number);
        }
    }

    public void updateTask(Task task, String description) {
        if (taskList.contains(task)) {
            taskList.get(taskList.indexOf(task)).setDescription(description);
        }
    }

    public void updateTask(int hour, Task task) {
        if (taskList.contains(task)) {
            taskList.get(taskList.indexOf(task)).setHours(hour);
        }
    }

    public void assignResourceToTask(Task task, int resourceId) {
        if (taskList.contains(task)) {
            task.setResourceId(resourceId);
        }
    }

    public void unassignResourceFromTask(Task task, Resource resource) {
        if (taskList.contains(task)) {
            task.setResourceId(0);
        }
    }

    public int calculateTaskDuration(Task task) {
        return taskList.get(taskList.indexOf(task)).getHours();
    }

    public int calculateAllTaskDuration() {
        int sum = 0;
        for (Task task : taskList) {
            sum += calculateTaskDuration(task);
        }
        return sum;
    }

    public void removeResourceFromAllTasks(int resourceId) {
        for (Task task : taskList) {
            task.removeResource(resourceId);
        }
    }

    public void printAllTasksOfDuration(){
        for (Task task : taskList) {
            System.out.println("Task Id: " + task.getNumber() + "    Duration: " + calculateTaskDuration(task));
        }
    }

    /*
    public List<Employee> getEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        for (Task task : taskList) {
            for (Employee employee : task.getEmployees()) {
                if (!employeeList.contains(employee)) {
                    employeeList.add(employee);
                }
            }
        }
        return employeeList;
    }

    public List<Consultant> getConsultants() {
        List<Consultant> consultantList = new ArrayList<>();
        for (Task task : taskList) {
            for (Consultant consultant : task.getConsultants()) {
                if (!consultantList.contains(consultant)) {
                    consultantList.add(consultant);
                }
            }
        }
        return consultantList;
    }
    */
}
