package com.github.miyashy.karatesample.infrastructure;

import com.github.miyashy.karatesample.domain.Todo;
import com.github.miyashy.karatesample.domain.repository.TodoRepository;
import com.github.miyashy.karatesample.infrastructure.persistence.TodoEntity;
import com.github.miyashy.karatesample.infrastructure.persistence.TodoJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class TodoRepositoryAdapter implements TodoRepository {
    private final TodoJdbcRepository jdbcRepository;

    @Override
    public Todo save(Todo todo) {
        // 既存エンティティの場合はversionを取得
        Long version = jdbcRepository.findById(todo.getId())
            .map(TodoEntity::version)
            .orElse(null);

        TodoEntity entity = new TodoEntity(
            todo.getId(),
            todo.getStatus().name(),
            todo.getUserId(),
            todo.getDescription(),
            version
        );
        TodoEntity saved = jdbcRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Todo find(UUID id) {
        return jdbcRepository.findById(id)
            .map(TodoEntity::toDomain)
            .orElse(null);
    }

    @Override
    public void delete(Todo todo) {
        jdbcRepository.deleteById(todo.getId());
    }
}
