package com.github.miyashy.karatesample.infrastructure

import com.github.miyashy.karatesample.domain.Todo
import com.github.miyashy.karatesample.domain.repository.TodoRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class TodoRepositoryImpl: TodoRepository {
    val map: MutableMap<UUID, Todo> = mutableMapOf()
    override fun save(todo: Todo): Todo {
        map[todo.id] = todo
        return todo
    }

    override fun find(id: UUID): Todo? {
        return map[id]
    }

    override fun delete(todo: Todo) {
        map.remove(todo.id)
    }
}