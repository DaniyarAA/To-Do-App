import enums.Priority;
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
        taskReader = new TaskReader();
        taskList = taskReader.loadTasks();
        if (taskList == null){
            taskList = new ArrayList<>();
        }
    }

    public void showMenu(){
        while (true){
            System.out.println("\nВыберите действие:\n" +
                    "1 - Показать все задачи\n" +
                    "2 - Показать определенную задачу с дополнительной информацией\n" +
                    "3 - Добавить новую задачу\n" +
                    "4 - Удалить задачу\n" +
                    "5 - Изменить статус задачи\n" +
                    "6 - Показать задачи по определенной сортировке\n" +
                    "0 - Выйти");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    printAllTasks();
                    break;
                case 2:
                    System.out.print("Введите номер задачи для отображения: ");
                    int taskNumber = scanner.nextInt();
                    printTask(taskNumber - 1);
                    break;
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
                    changeTaskStatus(changeStatusNumber - 1);
                    break;
                case 6:
                    showSortMenu();
                    break;
                case 7:
                    taskReader.saveTasks(taskList);
                    System.out.println("Выход из программы");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте заново");
                    break;
            }
        }
    }


    public void addTask() {
        try {
            System.out.println("Введите название задачи:");
            String title = scanner.nextLine();

            System.out.println("Введите описание задачи:");
            String description = scanner.nextLine();

            System.out.println("Введите дату завершения задачи (в формате dd.MM.yyyy):");
            String completionDateString = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate completionDate = LocalDate.parse(completionDateString, formatter);

            System.out.println("Введите приоритет задачи (низкий, средний, высокий): ");
            String priorityString = scanner.nextLine().toLowerCase();
            Priority priority = switch (priorityString) {
                case "низкий" -> Priority.LOW;
                case "средний" -> Priority.MEDIUM;
                case "высокий" -> Priority.HIGH;
                default ->
                        throw new IllegalArgumentException("Некорректный приоритет. Доступные значения: низкий, средний, высокий.");
            };

            LocalDateTime createDate = LocalDateTime.now();
            LocalDateTime completionDateTime = completionDate.atTime(23, 59, 59);
            Task task = new Task(title, description, createDate, completionDateTime, priority);

            taskList.add(task);
            taskReader.saveTasks(taskList);
            System.out.println("Задача добавлена");
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Ошибка при вводе даты. Убедитесь, что дата введена в формате dd.MM.yyyy.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка при вводе данных. Попробуйте заново.");
            e.printStackTrace();
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

    public void changeTaskStatus(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.get(index);
            try {
                task.getStatus().changeStatus(task);
                taskReader.saveTasks(taskList);
                System.out.println("Статус задачи изменен.");
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Неверный номер задачи.");
        }
    }

    public void printAllTasks() {
        if(taskList.isEmpty()){
            System.out.println("Список задач пуст. Создайте новую");
        }
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String overdue = task.getCompletionDate().isBefore(now) ? " *просрочено*" : "";
            System.out.printf("%d) %s%s (приоритет: %s)\n", i + 1, task.getTitle(), overdue, task.getPriority());
        }
    }

    public void printTask(int number){
        System.out.printf("\nНазвание задачи: %s" +
                        "\nОписание задачи: %s " +
                        "\nДата создания задачи: %s" +
                        "\nДата завершения задачи: %s" +
                        "\nПриоритет задачи: %s" +
                        "\nСтатус задачи: %s\n", taskList.get(number).getTitle(),
                taskList.get(number).getDescription(),
                taskList.get(number).getCreateDate(),
                taskList.get(number).getCompletionDate(),
                taskList.get(number).getPriority(),
                taskList.get(number).getStatus().getDescription()
                );
    }

    public void showSortMenu(){
        System.out.println("Выберите критерий сортировки:\n" +
                "1 - По приоритету\n" +
                "2 - По описанию\n" +
                "3 - По дате создания");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                sortTasksByPriority();
                break;
            case 2:
                sortTasksByDescription();
                break;
            case 3:
                sortByCreatedDate();
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте заново");
        }
        printAllTasks();
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
