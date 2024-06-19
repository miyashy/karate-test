package com.github.miyashy.karatesample.controller;

import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerTest {
    @LocalServerPort
    int port;

    @Karate.Test
    Karate test() {
        return Karate.run("classpath:todo.feature")
            .systemProperty("port", String.valueOf(port))
            .relativeTo(getClass());
    }
}
