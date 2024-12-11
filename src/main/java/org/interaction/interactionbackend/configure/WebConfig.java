package org.interaction.interactionbackend.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080") // the address allowed to access
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS") // allowed methods
                .allowedHeaders("*") // allowed headers
                .allowCredentials(true); // allow credentials
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/add")
                .excludePathPatterns("/event/3/getAll")
                .excludePathPatterns("/error")
                .excludePathPatterns("/service/getAll")
                .excludePathPatterns("/service/getCollection/**")
                .excludePathPatterns("/service/getFans/**")
                .excludePathPatterns("/getAllPhotos/**")
                .excludePathPatterns("/photo/fetchPhotos")
                .excludePathPatterns("/photo/fetchPhotosByTheme")
                .excludePathPatterns("/photo/fetchPhotosByEmail")
//                .excludePathPatterns("/image/upload")
                .order(1);
    }
}

