package com.github.miyashy.karatesample.controller;

import com.intuit.karate.junit5.Karate;

public class UserControllerTest {
    Karate test() {
        return Karate.run("classpath:user.feature");
    }
}
