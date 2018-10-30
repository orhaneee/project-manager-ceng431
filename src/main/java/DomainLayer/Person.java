package DomainLayer;

import java.util.List;

/**
 * Abstract class which also implements Resource.
 * Due to fact that Person should not be instantiated,
 * it has to be abstract.
 */
public abstract class Person implements Resource {

    public Person() {}

    public abstract void setId(int id);

    public abstract int getId();

    public abstract List<Integer> getTaskList();

    public abstract int findTaskId(int number);

    public abstract void addTask(int taskId);

    public abstract void removeTask(int taskId);
}
