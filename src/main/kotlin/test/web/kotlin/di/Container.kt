package test.web.kotlin.di

import test.web.kotlin.service.auth.Auth0

class Container {
    companion object {
        val conf = Configure(System.getenv("APP_CONF") ?: "application.conf")

        @JvmField
        val auth0 = Auth0(
                conf.auth0Config.domain,
                conf.auth0Config.clientId,
                conf.auth0Config.clientSecret
        )
    }
}