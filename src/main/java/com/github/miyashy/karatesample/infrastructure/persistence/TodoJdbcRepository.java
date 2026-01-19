package com.github.miyashy.karatesample.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TodoJdbcRepository extends CrudRepository<TodoEntity, UUID> {
}
