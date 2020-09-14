package com.github.miyashy.karatesample

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class Controller {

    @GetMapping("echo")
    fun echo(): String {
        return "hello"
    }

    @PostMapping("sample")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(): Sample {
        return Sample("hogehoge", "body")
    }

    @GetMapping("sample/hogehoge")
    fun get() : Sample {
        return Sample("hogehoge", "body")
    }
}

data class Sample(val id : String, val body: String)