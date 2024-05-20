package enums;

import model.Task;

public enum Status {
    NEW("НОВЫЙ") {
        @Override
        public void changeStatus(Task task) {
            if (task.getStatus() == NEW) {
                System.out.println("Статус задачи изменен на - В ПРОЦЕССЕ");
                task.setStatus(IN_PROGRESS);
            } else {
                throw new IllegalStateException("Статус задачи можно изменить на 'В ПРОЦЕССЕ' только из статуса 'НОВЫЙ'");
            }
        }

        @Override
        public void changeDescription(Task task, String description) {
            task.setDescription(description);
        }

        @Override
        public void deleteTask(Task task) {
            System.out.println("Задача удалена");
        }
    },
    IN_PROGRESS("В ПРОЦЕССЕ") {
        @Override
        public void changeStatus(Task task) {
            if (task.getStatus() == IN_PROGRESS) {
                System.out.println("Статус задачи изменен на - СДЕЛАНО");
                task.setStatus(DONE);
            } else {
                throw new IllegalStateException("Статус задачи можно изменить на 'СДЕЛАНО' только из статуса 'В ПРОЦЕССЕ'");
            }
        }

        @Override
        public void changeDescription(Task task, String description) {
            throw new IllegalStateException("Нельзя изменить описание задачи со статусом 'В ПРОЦЕССЕ'");
        }

        @Override
        public void deleteTask(Task task) {
            throw new IllegalStateException("Нельзя удалить задачу со статусом 'В ПРОЦЕССЕ'");
        }
    },
    DONE("СДЕЛАНО") {
        @Override
        public void changeStatus(Task task) {
            throw new IllegalStateException("Нельзя изменить статус со статуса 'СДЕЛАНО'");
        }

        @Override
        public void changeDescription(Task task, String description) {
            throw new IllegalStateException("Нельзя изменить описание задачи со статусом 'СДЕЛАНО'");
        }

        @Override
        public void deleteTask(Task task) {
            throw new IllegalStateException("Нельзя удалить задачу со статусом 'СДЕЛАНО'");
        }
    };

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract void changeStatus(Task task);
    public abstract void changeDescription(Task task, String description);
    public abstract void deleteTask(Task task);
}
