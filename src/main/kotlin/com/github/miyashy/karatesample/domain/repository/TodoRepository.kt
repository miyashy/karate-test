package com.github.miyashy.karatesample.domain.repository

import com.github.miyashy.karatesample.domain.Todo
import java.util.*

interface TodoRepository {
    fun save(todo: Todo) : Todo
    fun find(id: UUID) : Todo?
    fun delete(todo: Todo)
}