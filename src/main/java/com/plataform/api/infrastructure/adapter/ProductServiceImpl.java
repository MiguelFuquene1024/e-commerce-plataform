package com.plataform.api.infrastructure.adapter;

import com.plataform.api.application.service.ProductService;
import com.plataform.api.domain.Product;
import com.plataform.api.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {

        productRepository.save(product);
        return product;
    }

    @Override
    public Product getProduct(String productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(String productId) {

    }
}
