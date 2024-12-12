package com.plataform.api.application.configuration;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    public WebProperties.Resources webResources() {
        return new WebProperties.Resources();
    }
}
