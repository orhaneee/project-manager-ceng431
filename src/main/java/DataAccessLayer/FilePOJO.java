package DataAccessLayer;

import DomainLayer.Consultant;
import DomainLayer.Employee;
import DomainLayer.Project;
import DomainLayer.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * This class ensembles 'PLAIN OLD JAVA OBJECT' as POJO.
 * It is the model class for JSON as we write and read
 * as Java object and this class makes this easier.
 *
 * It has 3 attributes due to fact that
 * GSON can not handle interfaces that do not have
 * public constructor.
 *
 */
public class FilePOJO {
    private List<Project> projectList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    private List<Consultant> consultantList = new ArrayList<>();

    /**
     * Constructor to set lists.
     * @param projects projectList
     * @param resources resourceList
     */
    public FilePOJO(List<Project> projects, List<Resource> resources) {
        this.projectList = projects;
        for (Resource resource : resources) {
            if (resource.getClass() == Employee.class) {
                this.employeeList.add((Employee) resource);
            }
            else {
                this.consultantList.add((Consultant) resource);
            }
        }
    }

    public FilePOJO() {}

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setConsultantList(List<Consultant> consultantList) {
        this.consultantList = consultantList;
    }

    public List<Consultant> getConsultantList() {
        return consultantList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
