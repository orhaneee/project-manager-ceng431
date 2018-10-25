package DomainLayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {

    public Project(String name, String description, Date startDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
    }

    private String name;

    private String description;

    private Date startDate;

    private List<Activity> activityList = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void addActivity(Activity activity){
        activityList.add(activity);
    }

    public void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    public void updateActivity(Activity activity, int number) {
        if (activityList.contains(activity)) {
            activityList.get(activityList.indexOf(activity))
                    .setNumber(number);
        }
    }

    public void updateActivity(Activity activity, String description) {
        if (activityList.contains(activity)) {
            activityList.get(activityList.indexOf(activity))
                    .setDescription(description);
        }
    }

    public void updateActivity(String deliverable, Activity activity) {
        if (activityList.contains(activity)) {
            activityList.get(activityList.indexOf(activity))
                    .setDeliverable(deliverable);
        }
    }

    public int calculateActivityDuration(Activity activity) {
        return activity.calculateAllTaskDuration();
    }

    public int calculateAllActivityDuration() {
        int sum = 0;

        for (Activity activity : activityList) {
            sum += calculateActivityDuration(activity);
        }

        return sum;
    }

    public List<Employee> getEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        for (Activity activity : activityList) {
            for (Employee employee : activity.getEmployees()) {
                if (!employeeList.contains(employee)) {
                    employeeList.add(employee);
                }
            }
        }
        return employeeList;
    }
    
    public List<Consultant> getConsultans() {
        List<Consultant> consultantList = new ArrayList<>();
        for (Activity activity : activityList) {
            for (Consultant consultant : activity.getConsultants()) {
                if (!consultantList.contains(consultant)) {
                    consultantList.add(consultant);
                }
            }
        }
        return consultantList;
    }
}
