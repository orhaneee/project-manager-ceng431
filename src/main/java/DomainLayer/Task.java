package DomainLayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    public Task(int number, String description,Date startDate, int hours){
        this.number = number;
        this.description = description;
        this.startDate = startDate;
        this.hours = hours;
    }

    private List<Resource> resourceList = new ArrayList<>();

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

    public void addResource(Resource resource) {
        resourceList.add(resource);
    }

    public void removeResource(Resource resource) {
        resourceList.remove(resource);
    }

    public List<Employee> getEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        for (Resource resource : resourceList) {
            if (resource instanceof Employee) {
                if (!employeeList.contains(resource)) {
                    employeeList.add((Employee) resource);
                }
            }
        }
        return employeeList;
    }

    public List<Consultant> getConsultans() {
        List<Consultant> consultantList = new ArrayList<>();
        for (Resource resource : resourceList) {
            if (resource instanceof Consultant) {
                if (!consultantList.contains(resource)) {
                    consultantList.add((Consultant) resource);
                }
            }
        }
        return consultantList;
    }

}
