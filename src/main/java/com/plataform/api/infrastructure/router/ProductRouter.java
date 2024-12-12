package com.plataform.api.infrastructure.router;


import com.plataform.api.infrastructure.handler.ProductHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
@Slf4j
public class ProductRouter {

    private static final String PATH = "product";

    @Bean
    public WebProperties.Resources resources(){
        return new WebProperties.Resources();
    }
    @Bean
    RouterFunction<ServerResponse> router(ProductHandler handler) {
        return RouterFunctions.route()
                .GET(PATH,handler::getProducts)
                .GET(PATH + "/{id}",handler::getProduct)
                .POST(PATH,handler::createProduct)
                .PUT(PATH + "/{id}",handler::updateProduct)
                .DELETE(PATH + "/{id}",handler::deteleProduct)
                .build();
    }

}
