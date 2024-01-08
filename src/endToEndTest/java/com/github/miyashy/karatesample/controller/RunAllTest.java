package com.github.miyashy.karatesample.controller;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * 全件実行用のRunner
 */
public class RunAllTest {

    @Tag("all")
    @Test
    void runAllTest() {
        Results results = Runner.path("classpass:").tags("~@ignore").parallel(1);
        Assertions.assertEquals(0, results.getFailCount());
    }
}
