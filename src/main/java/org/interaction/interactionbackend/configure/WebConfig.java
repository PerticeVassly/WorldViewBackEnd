package org.interaction.interactionbackend.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许所有域名访问
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080") // the address allowed to access
                .allowedMethods("GET", "POST", "PUT", "DELETE") // allowed methods
                .allowedHeaders("*") // allowed headers
                .allowCredentials(true); // allow credentials
    }
}

