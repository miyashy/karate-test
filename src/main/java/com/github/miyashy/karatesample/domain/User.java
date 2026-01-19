package com.github.miyashy.karatesample.domain;

import lombok.Data;
import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String name;

    public static User newUser(String name) {
        User user = new User();
        user.id = UUID.randomUUID();
        user.name = name;
        return user;
    }

    public static User newUser(UUID id, String name) {
        User user = new User();
        user.id = id;
        user.name = name;
        return user;
    }
}