package com.github.miyashy.karatesample.domain;

import lombok.Data;
import java.util.UUID;

@Data
public class Todo {
    private UUID id;
    private TodoStatus status;
    private UUID userId;
    private String description;

    private Todo(UUID id, TodoStatus status, UUID userId, String description) {
        this.id = id;
        this.status = status;
        this.userId = userId;
        this.description = description;
    }

    public static Todo newTodo(UUID userId, String description) {
        return new Todo(UUID.randomUUID(), TodoStatus.TODO, userId, description);
    }

    public Todo updateStatus(TodoStatus changedStatus) {
        return new Todo(this.id, changedStatus, this.userId, this.description);
    }
    public  enum TodoStatus {
        TODO,
        IN_PROGRESS,
        DONE
    }
}
