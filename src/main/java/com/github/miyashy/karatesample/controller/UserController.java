package com.github.miyashy.karatesample.controller;

import com.github.miyashy.karatesample.domain.User;
import com.github.miyashy.karatesample.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User post(@RequestBody UserPostRequest request) {
        return userService.add(request.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }

    @Data
    public static class UserPostRequest {
        private String name;
    }
}