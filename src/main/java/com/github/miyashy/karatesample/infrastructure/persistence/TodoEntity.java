package com.github.miyashy.karatesample.infrastructure.persistence;

import com.github.miyashy.karatesample.domain.Todo;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("todos")
public record TodoEntity(
    @Id UUID id,
    String status,
    @Column("user_id") UUID userId,
    String description,
    @Version Long version
) implements Persistable<UUID> {
    public static TodoEntity from(Todo todo) {
        return new TodoEntity(
            todo.getId(),
            todo.getStatus().name(),
            todo.getUserId(),
            todo.getDescription(),
            null  // 新規エンティティの場合はnull
        );
    }

    public Todo toDomain() {
        return new Todo(
            this.id,
            Todo.TodoStatus.valueOf(this.status),
            this.userId,
            this.description
        );
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return version == null;
    }
}
