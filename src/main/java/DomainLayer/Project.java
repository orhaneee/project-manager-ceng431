package DomainLayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * Project class
 */
public class Project {

    public Project(String name, String description, Date startDate,
                   List<Activity> activityList) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.activityList = activityList;
    }

    private String name;

    private String description;

    private Date startDate;

    private List<Activity> activityList;

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

    public List<Activity> getActivityList() {
        return activityList;
    }

    public Activity findActivity(int number) {
        for (Activity activity : activityList) {
            if (activity.getNumber() == number) {
                return activity;
            }
        }
        return null;
    }

    public Activity findActivityFromTaskId(int taskId) {
        for (Activity activity : activityList) {
            List<Task> taskList = activity.getTaskList();
            for (Task task : taskList) {
                if (task.getNumber() == taskId) {
                    return activity;
                }
            }
        }
        return null;
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public void removeActivity(int number) {
        Activity activity = findActivity(number);
        activityList.remove(activity);
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

    public void removeResourceFromAllActivities(int resourceId) {
        for (Activity activity : activityList) {
            activity.removeResourceFromAllTasks(resourceId);
        }
    }

    public void printAllActivitiesOfDuration() {
        for (Activity activity : activityList) {
            System.out.println("Activity name: "
                    + activity.getNumber()
                    + "     Duration: " + activity.calculateAllTaskDuration());
        }
    }

    public List<Integer> getDistinctResourceIds() {
        List<Integer> resourceIdList = new ArrayList<>();
        for (Activity activity : activityList) {
            for (int id : activity.getDistinctResourceIds()) {
                if (!resourceIdList.contains(id)) {
                    resourceIdList.add(id);
                }
            }
        }
        return resourceIdList;
    }
}
