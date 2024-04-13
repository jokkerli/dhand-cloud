package com.jokeer.dhand.config;

import com.jokeer.dhand.interpreter.LoginInterpreter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterpreter())
                .addPathPatterns("/**");
    }

    @Bean
    public LoginInterpreter loginInterpreter() {
        return new LoginInterpreter();
    }
}
