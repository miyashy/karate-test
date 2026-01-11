package com.github.miyashy.karatesample.controller;

import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

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
