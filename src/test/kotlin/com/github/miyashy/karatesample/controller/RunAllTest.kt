package com.github.miyashy.karatesample.controller

import com.intuit.karate.Runner
import net.masterthought.cucumber.Configuration
import net.masterthought.cucumber.ReportBuilder
import org.apache.commons.io.FileUtils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File


/**
 * 全件実行用のRunner
 * Karateのテストレポート機能は並列実行時に欠損してしまうため個別のRunnerを定義する
 */
class RunAllTest {

    @Tag("all")
    @Test
    fun runAllTest() {
        val results = Runner.path("classpath:").tags("~@ignore").parallel(2)
        val jsonFiles = FileUtils.listFiles(File(results.reportDir), arrayOf("json"), true)
        val jsonPaths = jsonFiles.map { it.absolutePath }
        val config = Configuration(File(results.reportDir), "demo")
        val reportBuilder = ReportBuilder(jsonPaths, config)
        reportBuilder.generateReports()
        Assertions.assertEquals(0, results.failCount)
    }
}