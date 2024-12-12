package com.plataform.api.infrastructure.controller;

import com.plataform.api.application.service.ProductService;
import com.plataform.api.domain.ProductDto;
import com.plataform.api.infrastructure.adapter.ProductServiceImpl;
import com.plataform.api.infrastructure.repository.model.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductServiceImpl productService;


    @GetMapping("/get")
    public Mono<Product> getProduct( @PathVariable Integer id) {
        return productService.getProduct(id);
    }
    @GetMapping("/get-users")
    public Flux<Product> getProducts() {
        return productService.getAllProducts();
    }
    @PostMapping("/create")
    public Mono<Product> createProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }
    @PutMapping("/update")
    public Mono<Product> updateProduct(@Valid @PathVariable Integer id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id,productDto);
    }

    @DeleteMapping("/delete")
    public Mono<Void> deleteProduct(@RequestBody Integer id) {
        return productService.deleteProduct(id);
    }

}
