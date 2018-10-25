package DomainLayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity {

    public Activity(int number, String description, Date startDate, String deliverable) {
        this.number = number;
        this.description = description;
        this.startDate = startDate;
        this.deliverable = deliverable;
    }

    private int number;

    private String description;

    private Date startDate;

    private String deliverable;

    private List<Task> taskList = new ArrayList<>();

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

    public void updateTask(Task task, int number){
        if (taskList.contains(task)){
            taskList.get(taskList.indexOf(task)).setNumber(number);
        }
    }

    public void updateTask(Task task, String description) {
        if (taskList.contains(task)){
            taskList.get(taskList.indexOf(task)).setDescription(description);
        }
    }

    public void updateTask(int hour, Task task) {
        if (taskList.contains(task)){
            taskList.get(taskList.indexOf(task)).setHours(hour);
        }
    }

    public void updateTask(Task task, int resourceId, int a) {
        if (taskList.contains(task)){
            taskList.get(taskList.indexOf(task)).setResourceId(resourceId);
        }
    }

    public void assignResourceToTask(Task task, Resource resource) {
        if(task.getResourceId() != resource.getId()){
            task.setResourceId(resource.getId());
        }
    }

    public void unassignResourceToTask(Task task, Resource resource) {
        taskList.get(taskList.indexOf(task)).setResourceId(0);
    }

    public int calculateTaskDuration(Task task) {
        return taskList.get(taskList.indexOf(task)).getHours();
    }

    public int calculateAllTaskDuration(){
        int sum = 0;

        for(Task task : taskList){
            sum += calculateTaskDuration(task);
        }

        return sum;
    }

}
