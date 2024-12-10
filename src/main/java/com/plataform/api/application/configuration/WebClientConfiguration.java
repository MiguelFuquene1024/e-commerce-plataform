package com.plataform.api.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientConfiguration {

    @Bean
    public WebClient webClient() {
        String bootcampUrl = "http://localhost:8081";
        return WebClient.builder().baseUrl(bootcampUrl).build();
    }
}
