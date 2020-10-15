package com.github.miyashy.karatesample.domain

import java.util.*

data class Todo(val id: UUID, val status: TodoStatus, val userId: UUID, val description: String) {
    companion object {
        fun newTodo(userId: UUID, description: String) : Todo {
            return Todo(UUID.randomUUID(), TodoStatus.TODO, userId, description)
        }
    }

    fun updateStatus(changedStatus: TodoStatus) : Todo {
        return copy(status = changedStatus)
    }
}

enum class TodoStatus {
    TODO,
    IN_PROGRESS,
    DONE
}