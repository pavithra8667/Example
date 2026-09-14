package com.infinite.ehrSystem.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtAuthConverterConfig {

    @Bean
    public JwtAuthConverter jwtAuthConverter() {
        return new JwtAuthConverter();
    }
}