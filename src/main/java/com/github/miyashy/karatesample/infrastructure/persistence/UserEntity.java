package com.github.miyashy.karatesample.infrastructure.persistence;

import com.github.miyashy.karatesample.domain.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("users")
public record UserEntity(
    @Id UUID id,
    String name,
    @Version Long version
) implements Persistable<UUID> {
    public static UserEntity from(User user) {
        return new UserEntity(user.getId(), user.getName(), null);
    }

    public User toDomain() {
        return User.newUser(this.id, this.name);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return version == null;
    }
}
