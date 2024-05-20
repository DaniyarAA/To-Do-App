import model.Task;
import util.TaskReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskManager {
    private List<Task> taskList;
    private TaskReader taskReader;
    private Scanner scanner = new Scanner(System.in);

    public TaskManager(){
        taskList = taskReader.loadTasks();
    }

    public void showMenu(){
        while (true){
            System.out.println("Выберите действие:\n" +
                    "1 - Показать все задачи\n" +
                    "2 - Показать одну задачу с описанием\n" +
                    "3 - Добавить новую задачу\n" +
                    "4 - Удалить задачу\n" +
                    "5 - Изменить статус задачи\n" +
                    "6 - Показать задачу по определенной сортировке\n" +
                    "0 - Выйти");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    printAllTasks();
                    break;
                case 2:
                    System.out.print("Введите номер задачи для отображения: ");
                    int taskNumber = scanner.nextInt();
                    printTask(taskNumber - 1);
                case 3:
                    addTask();
                    break;
                case 4:
                    System.out.print("Введите номер задачи для удаления: ");
                    int deleteNumber = scanner.nextInt();
                    deleteTask(deleteNumber - 1);
                    break;
                case 5:
                    System.out.print("Введите номер задачи для изменения статуса: ");
                    int changeStatusNumber = scanner.nextInt();
                    break;
                case 6:
                    break;
                case 7:
                    taskReader.saveTasks(taskList);
                    System.out.println("Выход из программы");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте заново");
            }
        }

    }


    public void addTask(){
        try {
            System.out.println("Введите название задачи:");
            String title = scanner.nextLine();
            System.out.println("Введите описание задачи:");
            String description = scanner.nextLine();
            System.out.println("Введите дату завершения задачи (в формате dd.mm.yyyy):");
            String completionDateString = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate completionDate = LocalDate.parse(completionDateString, formatter);
            System.out.println("Введите приоритет задачи (низкий, средний, высокий): ");
            String priority = scanner.nextLine();
            LocalDateTime createDate = LocalDateTime.now();

            Task task = new Task(title, description, createDate, completionDate.atStartOfDay(), priority);
            taskList.add(task);
            taskReader.saveTasks(taskList);
            System.out.println("Задача добавлена");
        } catch (Exception e){
            System.out.println("Ошибка при вводе данных. Попробуйте заново");
        }
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.get(index);
            try {
                task.getStatus().deleteTask(task);
                taskList.remove(index);
                taskReader.saveTasks(taskList);
                System.out.println("Задача удалена.");
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Неверный номер задачи.");
        }
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
