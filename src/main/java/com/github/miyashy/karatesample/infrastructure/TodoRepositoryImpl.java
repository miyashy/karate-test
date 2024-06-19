package com.github.miyashy.karatesample.infrastructure;

import com.github.miyashy.karatesample.domain.Todo;
import com.github.miyashy.karatesample.domain.repository.TodoRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class TodoRepositoryImpl implements TodoRepository {
    private final Map<UUID, Todo> map = new HashMap<>();

    @Override
    public Todo save(Todo todo) {
        map.put(todo.getId(), todo);
        return todo;
    }

    @Override
    public Todo find(UUID id) {
        return map.get(id);
    }

    @Override
    public void delete(Todo todo) {
        map.remove(todo.getId());
    }
}