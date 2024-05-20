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

    public void showMenu(){
        System.out.println("Выберите действие:\n" +
                "1 - Показать все задачи\n" +
                "2 - Показать одну задачу с описанием\n" +
                "3 - Добавить новую задачу\n" +
                "4 - Удалить задачу\n" +
                "5 - Показать задачу по определенной сортировке");
    }
    public void addTask(Task task){
        taskList.add(task);
        taskReader.saveTasks(taskList);
    }

    public void deleteTask(Task task){
        taskList.remove(task);
        taskReader.saveTasks(taskList);
    }

    public void printAllTasks(){
        for (int i = 0; i < taskList.size(); i++){
            System.out.printf(" %s) %s ", i + 1, taskList.get(i).getTitle());
        }
    }

    public void printTask(int number){
        System.out.printf("\nНазвание задачи: %s" +
                        "\nОписание задачи: %s " +
                        "\nДата создания задачи: %s" +
                        "\nДата завершения задачи: %s" +
                        "\nПриоритет задачи: %s" +
                        "\nСтатус задачи: %s", taskList.get(number).getTitle(),
                taskList.get(number).getDescription(),
                taskList.get(number).getCreateDate(),
                taskList.get(number).getCompletionDate(),
                taskList.get(number).getPriority(),
                taskList.get(number).getStatus().getDescription()
                );
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
