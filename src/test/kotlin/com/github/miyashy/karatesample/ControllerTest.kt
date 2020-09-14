package com.github.miyashy.karatesample

import com.intuit.karate.junit5.Karate

class ControllerTest {
    @Karate.Test
    fun test(): Karate {
        return Karate.run("classpath:sample.feature").relativeTo(javaClass);
    }
}