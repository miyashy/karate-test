package com.github.miyashy.karatesample.controller

import com.github.miyashy.karatesample.domain.User
import com.github.miyashy.karatesample.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@RequestBody request: UserPostRequest): User {
        return userService.add(request.name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        userService.delete(id)
    }
}

data class UserPostRequest(val name: String)