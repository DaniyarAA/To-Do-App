package model;

import enums.Priority;
import enums.Status;

import java.time.LocalDateTime;

public class Task {
    private String title;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime completionDate;
    private Priority priority;
    private Status status;

    public Task(String title, String description, LocalDateTime createDate, LocalDateTime completionDate, Priority priority) {
        this.title = title;
        this.description = description;
        this.createDate = createDate;
        this.completionDate = completionDate;
        this.priority = priority;
        this.status = Status.NEW;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = title;
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

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", finishDate=" + completionDate +
                ", priority='" + priority + '\'' +
                '}';
    }
}
