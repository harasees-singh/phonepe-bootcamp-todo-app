package todo_app.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import todo_app.enums.Status;

import java.time.LocalDateTime;

public class Todo {

    private String id;
    private Status status;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime targetDate;
    public Todo() {
        // for serialization
    }
    public Todo(String description, Status status, LocalDateTime startDate, LocalDateTime targetDate) {
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.targetDate = targetDate;
    }

    @JsonProperty
    public String getId() {
        return id;
    }
    @JsonProperty
    public Status getStatus() {
        return status;
    }
    @JsonProperty
    public String getDescription() {
        return description;
    }
    @JsonProperty
    public LocalDateTime getStartDate() {
        return startDate;
    }
    @JsonProperty
    public LocalDateTime getTargetDate() {
        return targetDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setTargetDate(LocalDateTime targetDate) {
        this.targetDate = targetDate;
    }

    public void setId(String id) {
        this.id = id;
    }
}
