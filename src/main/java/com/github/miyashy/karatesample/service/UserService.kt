package com.github.miyashy.karatesample.service

import com.github.miyashy.karatesample.domain.User
import com.github.miyashy.karatesample.domain.User.Companion.newUser
import com.github.miyashy.karatesample.domain.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(val userRepository: UserRepository) {
    fun add(name: String) : User {
        return userRepository.save(newUser(name))
    }

    fun delete(id: UUID) {
        userRepository.find(id)?.let(userRepository::delete)
    }
}