package com.github.miyashy.karatesample.domain

import java.util.*

data class User(val id: UUID, val name: String) {
    companion object {
        fun newUser(name: String) : User {
            return User(UUID.randomUUID(), name)
        }
    }
}