package DomainLayer;

import java.util.Date;

public class Task {
    private int number;

    private String description;

    private Date startDate;

    private int hours;

    private int resourceId;

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

    public void setResourceId(int id) {
        this.resourceId = id;
    }

    public int getResourceId() {
        return resourceId;
    }



}
