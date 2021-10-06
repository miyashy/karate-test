package com.github.miyashy.karatesample.controller

import com.intuit.karate.junit5.Karate


class UserControllerTest {
    @Karate.Test
    fun test(): Karate {
        return Karate.run("classpath:user.feature")
    }
}