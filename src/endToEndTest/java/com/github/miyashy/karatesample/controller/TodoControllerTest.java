package com.github.miyashy.karatesample.controller;

import com.intuit.karate.junit5.Karate;

public class TodoControllerTest {
    @Karate.Test
    Karate test() {
        return Karate.run("classpath:todo.feature");
    }
}
