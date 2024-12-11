package com.plataform.api.infrastructure.handler;

import com.plataform.api.domain.Product;
import com.plataform.api.infrastructure.adapter.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductHandler {


    private final ProductServiceImpl productService;


    public Mono<ServerResponse> getProducts(ServerRequest request) {
        Flux<Product> products = productService.getAllProducts();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(products, Product.class);
    }

    public Mono<ServerResponse> getProduct(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        Mono<Product> product = productService.getProduct(id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(product, Product.class);
    }

    public Mono<ServerResponse> createProduct(ServerRequest request) {
        Mono<Product> product = request.bodyToMono(Product.class);
        return product.flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.createProduct(p), Product.class));
    }

    public Mono<ServerResponse> updateProduct(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        Mono<Product> product = request.bodyToMono(Product.class);
        return product.flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.updateProduct(id,p), Product.class));
    }

    public Mono<ServerResponse> deteleProduct(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.deleteProduct(id), Product.class);
    }
}
