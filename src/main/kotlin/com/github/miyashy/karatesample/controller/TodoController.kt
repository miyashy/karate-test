package com.github.miyashy.karatesample.controller

import com.github.miyashy.karatesample.domain.Todo
import com.github.miyashy.karatesample.domain.TodoStatus
import com.github.miyashy.karatesample.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/todos")
class TodoController(val todoService: TodoService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@RequestBody request: TodoPostRequest): Todo {
        return todoService.add(request.userId, request.description);
    }

    @PatchMapping("/{id}")
    fun put(@PathVariable id: UUID, @RequestBody request: TodoPutRequest): Todo {
        return todoService.changeStatus(id, request.status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        todoService.delete(id)
    }
}

data class TodoPostRequest(val userId: UUID, val description: String)
data class TodoPutRequest(val status: TodoStatus)