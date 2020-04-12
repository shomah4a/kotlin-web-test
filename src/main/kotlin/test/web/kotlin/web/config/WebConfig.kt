package test.web.kotlin.web.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.view.InternalResourceViewResolver

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = ["test.web.kotlin.web.controller"])
open class WebConfig : WebMvcConfigurer {
    @Bean
    open fun viewResolver(): ViewResolver {
        val resolver = InternalResourceViewResolver()
        resolver.setExposeContextBeansAsAttributes(true)
        return resolver
    }

    override fun configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer) {
        configurer.enable()
    }
}