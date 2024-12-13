package com.plataform.api.infrastructure.adapter;

import com.plataform.api.application.exception.CustomException;
import com.plataform.api.application.service.ProductService;
import com.plataform.api.domain.ProductDto;
import com.plataform.api.infrastructure.repository.model.Product;
import com.plataform.api.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final static String NOT_FOUND_MESSAGE = "Product not found";

    private final static String NOT_REPEATED_NAME = "product name already in use";

    private final ProductRepository productRepository;


    @Override
    public Mono<Product> getProduct(Integer productId) {

        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND,NOT_FOUND_MESSAGE)));
    }

    @Override
    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> createProduct(ProductDto productDto) {
        Mono<Boolean> existsName = productRepository.findByName(productDto.getName()).hasElement();
        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .amount(productDto.getAmount())
                .build();
        return existsName.flatMap(exists -> exists? Mono.error(
                new CustomException(HttpStatus.BAD_REQUEST,NOT_REPEATED_NAME)):productRepository.save(product));
    }

    @Override
    public Mono<Product> updateProduct(Integer id,ProductDto productDto) {
        Mono<Boolean> productId = productRepository.findById(id).hasElement();
        Mono<Boolean> productRepeatedName = productRepository.findByName(productDto.getName()).hasElement();
        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .amount(productDto.getAmount())
                .build();
        return productId.flatMap(
                existsId -> existsId ?
                        productRepeatedName.flatMap(existsName -> existsName?Mono.error(
                                new CustomException(HttpStatus.BAD_REQUEST,NOT_REPEATED_NAME)) :
                                productRepository.save(product)) : Mono.error(
                                        new CustomException(HttpStatus.NOT_FOUND,NOT_FOUND_MESSAGE)));

    }

    @Override
    public Mono<Void> deleteProduct(Integer productId) {
        Mono<Boolean> existsId =  productRepository.findById(productId).hasElement();
        return existsId.flatMap(exists -> exists ? productRepository.deleteById(productId):
                Mono.error(new CustomException(HttpStatus.NOT_FOUND,NOT_FOUND_MESSAGE)));
    }
}
