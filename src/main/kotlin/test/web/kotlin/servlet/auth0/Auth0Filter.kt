package test.web.kotlin.servlet.auth0

import test.web.kotlin.di.Container
import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(filterName = "auth0-filter", urlPatterns = ["/secret"])
class Auth0Filter : Filter {
    override fun destroy() {}

    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        println("hello, filter")

        if (req is HttpServletRequest && res is HttpServletResponse) {
            val user = Container.auth0.auth(req, res)

            if (user != null) {
                chain.doFilter(req, res)
            } else {
                val redirectUrl = Container.auth0.makeAuthUtl(req, res, "http://localhost:8888/kotlin-web-test/auth/callback")
                res.sendRedirect(redirectUrl)
            }
        }
    }

    override fun init(filterConfig: FilterConfig?) {}
}