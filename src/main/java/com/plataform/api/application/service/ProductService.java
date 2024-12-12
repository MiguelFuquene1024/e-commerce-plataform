package com.plataform.api.application.service;

import com.plataform.api.domain.ProductDto;
import com.plataform.api.infrastructure.repository.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Product> createProduct(ProductDto productDto);

    Mono<Product> getProduct(Integer productId);

    Flux<Product> getAllProducts();

    Mono<Product> updateProduct(Integer id, ProductDto productDto);

    Mono<Void> deleteProduct(Integer productId);
}
