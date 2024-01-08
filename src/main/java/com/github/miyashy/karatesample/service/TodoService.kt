package com.github.miyashy.karatesample.service

import com.github.miyashy.karatesample.domain.Todo
import com.github.miyashy.karatesample.domain.TodoStatus
import com.github.miyashy.karatesample.domain.repository.TodoRepository
import com.github.miyashy.karatesample.domain.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class TodoService(val todoRepository: TodoRepository,
                  val userRepository: UserRepository) {
    fun add(userId: UUID, description: String): Todo {
        val user = userRepository.find(userId) ?: throw ResponseStatusException(HttpStatus.CONFLICT)
        return todoRepository.save(Todo.newTodo(user.id, description))
    }

    fun changeStatus(id: UUID, status: TodoStatus): Todo {
        return todoRepository.find(id)
                ?.let { it -> todoRepository.save(it.updateStatus(status)) }
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun delete(id: UUID) {
        todoRepository.find(id)?.let { it -> todoRepository.delete(it) }
    }
}