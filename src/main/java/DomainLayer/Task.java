package DomainLayer;

import java.util.Date;

public class Task {
    public Task(int number, String description,Date startDate, int hours) {
        this.number = number;
        this.description = description;
        this.startDate = startDate;
        this.hours = hours;
        this.resourceId = 0;
    }

    private int resourceId;

    private int number;

    private String description;

    private Date startDate;

    private int hours;

    public void setResourceId(int resourceId) {
        if(resourceId > 0){
            this.resourceId = resourceId;
        }
    }

    public int getResourceId() {
        return resourceId;
    }

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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void removeResource(int resourceId) {
        if (resourceId == getResourceId()) {
            setResourceId(0);
        }
    }


}
