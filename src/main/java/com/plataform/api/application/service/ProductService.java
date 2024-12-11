package com.plataform.api.application.service;

import com.plataform.api.domain.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductService {

    Mono<Product> createProduct(Product product);

    Mono<Product> getProduct(Integer productId);

    Flux<Product> getAllProducts();

    Mono<Product> updateProduct(Integer id,Product product);

    Mono<Void> deleteProduct(Integer productId);
}
