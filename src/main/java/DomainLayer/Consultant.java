package DomainLayer;

import java.util.ArrayList;
import java.util.List;

public class Consultant extends Person {

    private int id;
    private List<Task> taskList = new ArrayList<>();

    public Consultant(int id){
        this.id = id;
    }

    //consultant id should be lower than 1001, since employee id should be bigger than 1000.
    @Override
    public void setId(int id) {
        if ((0 < id) && (id <= 1000)) {
            this.id = id;
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<Task> getTaskList() {
        return taskList;
    }

    @Override
    public Task findTask(int number) {
        for(Task task : taskList) {
            if(task.getNumber() == number) {
                return task;
            }
        }
        return null;
    }

    @Override
    public void addTask(Task task){
        if (!taskList.contains(task)){
            taskList.add(task);
        }
    }

    @Override
    public void removeTask(Task task) {
        taskList.remove(task);
    }

}
