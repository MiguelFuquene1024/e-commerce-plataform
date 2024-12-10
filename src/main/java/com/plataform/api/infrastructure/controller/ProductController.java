package com.plataform.api.infrastructure.controller;

import com.plataform.api.application.service.ProductService;
import com.plataform.api.domain.Product;
import com.plataform.api.infrastructure.adapter.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @PutMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @GetMapping("/get-user")
    public ResponseEntity<?> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }
    @GetMapping("/get-users")
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

}
