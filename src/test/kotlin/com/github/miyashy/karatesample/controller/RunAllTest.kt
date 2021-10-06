package com.github.miyashy.karatesample.controller

import com.intuit.karate.Runner
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test


/**
 * 全件実行用のRunner
 */
class RunAllTest {

    @Tag("all")
    @Test
    fun runAllTest() {
        val results = Runner.path("classpath:").tags("~@ignore").parallel(2)
        Assertions.assertEquals(0, results.failCount)
    }
}