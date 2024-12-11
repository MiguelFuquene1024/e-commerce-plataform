package com.plataform.api.infrastructure.adapter;

import com.plataform.api.application.service.ProductService;
import com.plataform.api.domain.Product;
import com.plataform.api.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Mono<Product> createProduct(Product product) {
        Mono<Boolean> existsName = productRepository.findByName(product.getName()).hasElement();
        return existsName.flatMap(exists -> exists? Mono.error(
                new Exception("product name already in use")):productRepository.save(product));
    }

    @Override
    public Mono<Product> getProduct(Integer productId) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new Exception("product not found")));
    }

    @Override
    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> updateProduct(Integer id,Product product) {
        Mono<Boolean> productId = productRepository.findById(product.getId()).hasElement();
        Mono<Boolean> productRepeatedName = productRepository.findByName(product.getName()).hasElement();
        product.setId(id);
        return productId.flatMap(
                existsId -> existsId ?
                        productRepeatedName.flatMap(existsName -> existsName?Mono.error(
                                new Exception("product name already in use")) : productRepository.save(product)) : Mono.error(
                                        new Exception("product not found"));

    }

    @Override
    public Mono<Void> deleteProduct(Integer productId) {
        Mono<Boolean> existsId =  productRepository.findById(productId).hasElement();
        return existsId.flatMap(exists -> exists ? productRepository.deleteById(productId):Mono.error(new Exception("product not found")));
    }
}
