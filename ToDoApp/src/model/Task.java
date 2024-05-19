package model;

import java.time.LocalDateTime;

public class Task {
    private String title;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime completionDate;
    private String priority;

    public Task(String title, String description, LocalDateTime createDate, LocalDateTime comletionDate, String priority) {
        this.title = title;
        this.description = description;
        this.createDate = createDate;
        this.completionDate = comletionDate;
        this.priority = priority;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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