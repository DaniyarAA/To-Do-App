package model;

import java.time.LocalDateTime;

public class Task {
    private String name;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime finishDate;
    private String priority;

    public Task(String name, String description, LocalDateTime createDate, LocalDateTime finishDate, String priority) {
        this.name = name;
        this.description = description;
        this.createDate = createDate;
        this.finishDate = finishDate;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", finishDate=" + finishDate +
                ", priority='" + priority + '\'' +
                '}';
    }
}
