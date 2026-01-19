package com.github.miyashy.karatesample.infrastructure;

import com.github.miyashy.karatesample.domain.User;
import com.github.miyashy.karatesample.domain.repository.UserRepository;
import com.github.miyashy.karatesample.infrastructure.persistence.UserEntity;
import com.github.miyashy.karatesample.infrastructure.persistence.UserJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class UserRepositoryAdapter implements UserRepository {
    private final UserJdbcRepository jdbcRepository;

    @Override
    public User save(User user) {
        // 既存エンティティの場合はversionを取得
        Long version = jdbcRepository.findById(user.getId())
            .map(UserEntity::version)
            .orElse(null);

        UserEntity entity = new UserEntity(
            user.getId(),
            user.getName(),
            version
        );
        UserEntity saved = jdbcRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public User find(UUID id) {
        return jdbcRepository.findById(id)
            .map(UserEntity::toDomain)
            .orElse(null);
    }

    @Override
    public void delete(User user) {
        jdbcRepository.deleteById(user.getId());
    }
}
