package DomainLayer;

import java.util.List;

public interface Resource {

    void setId(int id);

    int getId();

    List<Task> getTaskList();

    void addTask(Task task);

    void removeTask(Task task);

    Task findTask(int number);


}
