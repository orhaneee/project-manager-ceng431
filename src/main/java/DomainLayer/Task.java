package DomainLayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    public Task(int number, String description,Date startDate, int hours, List<Resource> resourceList) {
        this.number = number;
        this.description = description;
        this.startDate = startDate;
        this.hours = hours;
        this.resourceList = resourceList;
    }

    private List<Resource> resourceList;

    private int number;

    private String description;

    private Date startDate;

    private int hours;

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public List<Resource> getResourceList() {
        return resourceList;
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

    public void addResource(Resource resource) {
        if (!resourceList.contains(resource)) {
            resourceList.add(resource);
        }
    }

    public void removeResource(Resource resource) {
        if (resourceList.contains(resource)) {
            resourceList.remove(resource);
        }
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

    public List<Consultant> getConsultants() {
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
