package com.github.miyashy.karatesample.controller;

import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @LocalServerPort
    int port;

    @Karate.Test
    Karate test() {
        return Karate.run("classpath:user.feature")
            .systemProperty("port", String.valueOf(port))
            .relativeTo(getClass());
    }
}
