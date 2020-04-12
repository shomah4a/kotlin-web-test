package test.web.kotlin.service.auth

import com.auth0.AuthenticationController
import com.auth0.SessionUtils
import com.auth0.Tokens
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class User(accessToken: String, idToken: String)

class Auth0(domain: String, clientId: String, clientSecret: String) {

    val accessTokenName = "accessToken"
    val idTokenName = "idToken"

    val authController = AuthenticationController.newBuilder(domain, clientId, clientSecret).build()

    fun makeAuthUtl(req: HttpServletRequest, res: HttpServletResponse, redirectUrl: String): String {
        return authController.buildAuthorizeUrl(req, res, redirectUrl).build()
    }

    fun handle(req: HttpServletRequest, res: HttpServletResponse): User {
        val tokens = authController.handle(req, res)
        SessionUtils.set(req, accessTokenName, tokens.accessToken)
        SessionUtils.set(req, idTokenName, tokens.idToken)

        return User(tokens.accessToken, tokens.idToken)
    }

    fun auth(req: HttpServletRequest, res: HttpServletResponse): User? {
        val accessToken = SessionUtils.get(req, accessTokenName)
        val idToken = SessionUtils.get(req, idTokenName)


        return if (accessToken != null && idToken != null) {
            User(accessToken.toString(), idToken.toString())
        } else {
            null
        }
    }
}