package test.web.kotlin.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import javax.servlet.http.HttpServletResponse


@Controller
class HelloController {

    @GetMapping("/hoge")
    fun hello(res: HttpServletResponse) {
        res.contentType = "text/plain; charset=UTF-8"
        res.writer.println("hoge-----")
    }


    @GetMapping("/yo/{id}")
    fun yo(@PathVariable("id") id: Int, res: HttpServletResponse) {
        res.contentType = "text/plain; charset=UTF-8"
        res.writer.println(id)
    }

}