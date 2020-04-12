package test.web.kotlin.servlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "secret", urlPatterns = ["/secret"])
class Secret : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.contentType = "text/plain; charset=UTF-8"
        resp.writer!!.println("you can see this secret page")
    }
}