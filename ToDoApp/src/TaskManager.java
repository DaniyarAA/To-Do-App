import model.Task;
import util.TaskReader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskManager {
    private List<Task> taskList;
    private TaskReader taskReader;

    public TaskManager(){
        taskList = taskReader.loadTasks();
    }
    public void addTask(Task task){
        taskList.add(task);
        taskReader.saveTasks(taskList);
    }

    public void deleteTask(Task task){
        taskList.remove(task);
        taskReader.saveTasks(taskList);
    }

    public void printTasks(){
        for(Task task:taskList){
            System.out.println(task);
        }
    }

    public void sortTasksByPriority(){
        taskList.sort(Comparator.comparing(Task::getPriority).reversed());
    }

    public void sortTasksByDescription(){
        taskList.sort(Comparator.comparing(Task::getDescription));
    }

    public void sortByCreatedDate(){
        taskList.sort(Comparator.comparing(Task::getCreateDate));
    }
}
