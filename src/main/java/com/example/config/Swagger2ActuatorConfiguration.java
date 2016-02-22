package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger2ActuatorConfiguration {
    @Bean
    public Swagger2Endpoint swagger2Endpoint() {
        return new Swagger2Endpoint();
    }

    @Bean
    public Swagger2MvcEndpoint swagger2MvcEndpoint(Swagger2Endpoint swagger2Endpoint) {
        return new Swagger2MvcEndpoint(swagger2Endpoint);
    }
}
