package com.demo.cash_machine.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig {

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/*")
                    .allowedOrigins("") // Allow all origins
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow specific methods
                    .allowedHeaders("*") // Allow all headers
            }
        }
    }
}