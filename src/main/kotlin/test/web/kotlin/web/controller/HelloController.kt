package test.web.kotlin.web.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class HelloController {

    @GetMapping("/hoge")
    fun hello(): String {
        return "hogehoge"
    }


    @GetMapping("/yo/{id}")
    fun yo(@PathVariable("id") id: Int): Int {
        return id
    }

}