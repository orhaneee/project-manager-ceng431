package DomainLayer;

import java.util.List;

public interface Resource {

    void setId(int id);

    int getId();

    List<Integer> getTaskList();

    void addTask(int taskId);

    void removeTask(int taskId);

    int findTaskId(int number);


}
