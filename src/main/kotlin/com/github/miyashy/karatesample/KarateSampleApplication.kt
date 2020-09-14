package com.github.miyashy.karatesample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KarateSampleApplication

fun main(args: Array<String>) {
    runApplication<KarateSampleApplication>(*args)
}
