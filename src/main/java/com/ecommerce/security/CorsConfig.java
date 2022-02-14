package com.ecommerce.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig {

    @Configuration
    @EnableWebMvc
    public static class CorsConfiguration implements WebMvcConfigurer
    {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedMethods("GET", "POST", "DELETE");
        }
    }
}
