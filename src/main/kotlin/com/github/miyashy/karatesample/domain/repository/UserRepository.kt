package com.github.miyashy.karatesample.domain.repository

import com.github.miyashy.karatesample.domain.User
import java.util.*

interface UserRepository {
    fun save(user: User) : User
    fun find(id: UUID) : User?
    fun delete(user: User)
}