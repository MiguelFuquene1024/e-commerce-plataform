package com.plataform.api.application.service;

import com.plataform.api.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product getProduct(String productId);

    List<Product> getAllProducts();

    void updateProduct(Product product);

    void deleteProduct(String productId);
}
