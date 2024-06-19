package com.github.miyashy.karatesample.domain.repository;

import com.github.miyashy.karatesample.domain.Todo;

import java.util.UUID;

public interface TodoRepository {
    Todo save(Todo todo);
    Todo find(UUID id);
    void delete(Todo todo);
}