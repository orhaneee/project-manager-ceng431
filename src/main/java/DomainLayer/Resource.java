package DomainLayer;

import java.util.List;

/**
 * Resource interface.
 *
 * Although, there can be more approaches to use Resource,
 * maybe as class, we thought this is the best way.
 * Since, SOLID requires extensibility, we think that
 * adding many other classes which implements this interface
 * will be more robust and cleaner code.
 *
 * All the responsibilities that a resource should follow
 * defined here.
 *
 */
public interface Resource {

    void setId(int id);

    int getId();

    List<Integer> getTaskList();

    void addTask(int taskId);

    void removeTask(int taskId);

    int findTaskId(int number);
}
