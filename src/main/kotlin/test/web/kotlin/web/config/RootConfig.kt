package test.web.kotlin.web.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["test.web.kotlin.service"])
open class RootConfig {
}