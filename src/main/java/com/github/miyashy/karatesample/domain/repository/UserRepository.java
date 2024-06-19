package com.github.miyashy.karatesample.domain.repository;

import com.github.miyashy.karatesample.domain.User;

import java.util.UUID;

public interface UserRepository {
    User save(User user);
    User find(UUID id);
    void delete(User user);
}