package com.github.miyashy.karatesample.service;

import com.github.miyashy.karatesample.domain.User;
import com.github.miyashy.karatesample.domain.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User add(String name) {
        return userRepository.save(User.newUser(name));
    }

    public void delete(UUID id) {
        Optional.ofNullable(userRepository.find(id)).ifPresent(userRepository::delete);
    }
}