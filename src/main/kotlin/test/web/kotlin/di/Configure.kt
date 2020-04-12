package test.web.kotlin.di

import com.typesafe.config.ConfigFactory
import io.github.config4k.*
import java.io.File

data class Auth0Config(val domain: String, val clientId: String, val clientSecret: String) {}

class Configure(filepath: String) {
    val conf = ConfigFactory.parseFile(File(filepath))
    val auth0Config = conf.extract<Auth0Config>("auth0")
}