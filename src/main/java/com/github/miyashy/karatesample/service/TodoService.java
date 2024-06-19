package com.github.miyashy.karatesample.service;

import com.github.miyashy.karatesample.domain.Todo;
import com.github.miyashy.karatesample.domain.User;
import com.github.miyashy.karatesample.domain.repository.TodoRepository;
import com.github.miyashy.karatesample.domain.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public Todo add(UUID userId, String description) {
        User user = userRepository.find(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return todoRepository.save(Todo.newTodo(user.getId(), description));
    }

    public Todo changeStatus(UUID id, Todo.TodoStatus status) {
        Todo todo = todoRepository.find(id);
        if (todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return todoRepository.save(todo.updateStatus(status));
    }

    public void delete(UUID id) {
        Todo todo = todoRepository.find(id);
        if (todo != null) {
            todoRepository.delete(todo);
        }
    }
}