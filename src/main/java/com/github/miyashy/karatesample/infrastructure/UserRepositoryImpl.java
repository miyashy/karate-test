package com.github.miyashy.karatesample.infrastructure;

import com.github.miyashy.karatesample.domain.User;
import com.github.miyashy.karatesample.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<UUID, User> map = new HashMap<>();

    @Override
    public User save(User user) {
        map.put(user.getId(), user);
        return user;
    }

    @Override
    public User find(UUID id) {
        return map.get(id);
    }

    @Override
    public void delete(User user) {
        map.remove(user.getId());
    }
}