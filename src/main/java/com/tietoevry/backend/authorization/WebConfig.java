package com.tietoevry.backend.authorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).maxAge(36000);
        /*
                .allowedOrigins("*")
                .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS")
                .allowCredentials(true);
         */
    }
}
