package com.phegondev.phegon.productorderingsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig {

    @Bean 
    public WebMvcConfigurer WebMvcConfigurer(){
        return new WebMvcConfigurer(){

            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                .alloweMethods("GET","POST","PUT","DELETE")
                .allowedOrigins("*");
            }
        };
    }

}
