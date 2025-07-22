package com.poc.mvctohexagonal.application

import com.poc.mvctohexagonal.domain.port.GreetingServicePort
import com.poc.mvctohexagonal.domain.port.GreetingPort
import com.poc.mvctohexagonal.domain.service.GreetingService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GreetingConfig {
    @Bean
    fun greetingService(greetingPort: GreetingPort): GreetingServicePort =
        GreetingService(greetingPort)
}

