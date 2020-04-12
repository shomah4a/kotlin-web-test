package test.web.kotlin.servlet.auth0

import com.auth0.IdentityVerificationException
import test.web.kotlin.di.Container
import java.io.IOException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(name = "callback", urlPatterns = ["/auth/callback"])
class LoginCallback : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        handle(req, resp)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        handle(req, resp)
    }

    @Throws(IOException::class)
    private fun handle(req: HttpServletRequest, res: HttpServletResponse) {
        try {
            Container.auth0.handle(req, res)
            res.sendRedirect("/")
        } catch (e: IdentityVerificationException) {
            e.printStackTrace()
            res.sendRedirect("/404")
        }
    }
}