package com.plataform.api.application.configuration;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;

@Configuration
public class ApiConfig {

    @Bean
    public WebProperties.Resources webResources() {

        return new WebProperties.Resources();
    }

}
