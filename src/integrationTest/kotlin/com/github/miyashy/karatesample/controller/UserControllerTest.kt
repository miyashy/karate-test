package com.github.miyashy.karatesample.controller

import com.intuit.karate.junit5.Karate
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UserControllerTest {
    @Karate.Test
    fun test(): Karate {
        return Karate.run("classpath:user.feature")
    }
}