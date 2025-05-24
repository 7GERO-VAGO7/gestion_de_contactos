package com.contactos.contactos.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/") // Permite CORS para todas las rutas
                .allowedOrigins("http://localhost:3000") // Permite solicitudes desde http://localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Permite estos métodos HTTP
    }
}