package com.github.miyashy.karatesample.controller;

import com.github.miyashy.karatesample.domain.Todo;
import com.github.miyashy.karatesample.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo post(@RequestBody TodoPostRequest request) {
        return todoService.add(request.userId(), request.description());
    }

    @PatchMapping("/{id}")
    public Todo put(@PathVariable UUID id, @RequestBody TodoPutRequest request) {
        return todoService.changeStatus(id, request.status());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        todoService.delete(id);
    }

    public record TodoPostRequest(UUID userId, String description) {
    }

    public record TodoPutRequest(Todo.TodoStatus status) {
    }

}


