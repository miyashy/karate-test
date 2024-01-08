package com.github.miyashy.karatesample.infrastructure

import com.github.miyashy.karatesample.domain.User
import com.github.miyashy.karatesample.domain.repository.UserRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepositoryImpl: UserRepository{
    val map: MutableMap<UUID, User> = mutableMapOf()
    override fun save(user: User): User {
        map[user.id] = user
        return user
    }

    override fun find(id: UUID): User? {
        return map[id]
    }

    override fun delete(user: User) {
        map.remove(user.id)
    }
}