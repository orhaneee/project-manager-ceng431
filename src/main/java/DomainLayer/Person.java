package DomainLayer;

import java.util.List;

public abstract class Person implements Resource {

    public Person(String name) {
        this.name = name;

    }

    private String name;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public abstract void setId(int id);

    public abstract int getId();

    public abstract List<Task> getTaskList();

    public abstract Task findTask(int number);

    public abstract void addTask(Task task);

    public abstract void removeTask(Task task);


}
