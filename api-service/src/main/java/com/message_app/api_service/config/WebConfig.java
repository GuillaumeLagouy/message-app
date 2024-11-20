package com.message_app.api_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**") // Applique CORS à tous les endpoints
            .allowedOrigins("http://localhost:3000") // Autorise l'origine du frontend
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Autorise les méthodes HTTP
            .allowedHeaders("*") // Autorise tous les en-têtes
            .allowCredentials(true); // Autorise l'envoi de cookies (si nécessaire)
    }
}
