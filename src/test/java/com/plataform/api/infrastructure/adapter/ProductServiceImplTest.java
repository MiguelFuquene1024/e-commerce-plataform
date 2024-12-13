package com.plataform.api.infrastructure.adapter;

import com.plataform.api.application.exception.CustomException;
import com.plataform.api.domain.ProductDto;
import com.plataform.api.infrastructure.repository.ProductRepository;
import com.plataform.api.infrastructure.repository.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProduct_Success() {
        Product product = new Product(1, "Product1", "Description1", Float.valueOf(100), 10);
        when(productRepository.findById(1)).thenReturn(Mono.just(product));

        StepVerifier.create(productService.getProduct(1))
                .expectNext(product)
                .verifyComplete();

        verify(productRepository).findById(1);
    }

    @Test
    void testGetProduct_NotFound() {
        when(productRepository.findById(1)).thenReturn(Mono.empty());

        StepVerifier.create(productService.getProduct(1))
                .expectErrorMatches(throwable -> throwable instanceof CustomException &&
                        ((CustomException) throwable).getStatus() == HttpStatus.NOT_FOUND)
                .verify();

        verify(productRepository).findById(1);
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product(1, "Product1", "Description1", Float.valueOf(100), 10);
        Product product2 = new Product(2, "Product2", "Description2", Float.valueOf(200), 20);
        when(productRepository.findAll()).thenReturn(Flux.just(product1, product2));

        StepVerifier.create(productService.getAllProducts())
                .expectNext(product1, product2)
                .verifyComplete();

        verify(productRepository).findAll();
    }

    @Test
    void testCreateProduct_Success() {
        ProductDto productDto = new ProductDto("Product1", "Description1", Float.valueOf(100), 10);
        Product product = new Product(null, "Product1", "Description1", Float.valueOf(100), 10);
        when(productRepository.findByName("Product1")).thenReturn(Mono.empty());
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(product));

        StepVerifier.create(productService.createProduct(productDto))
                .expectNext(product)
                .verifyComplete();

        verify(productRepository).findByName("Product1");
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void testCreateProduct_NameAlreadyExists() {
        ProductDto productDto = new ProductDto("Product1", "Description1", Float.valueOf(100), 10);
        Product product = new Product(null, "Product1", "Description1", Float.valueOf(100), 10);
        when(productRepository.findByName("Product1")).thenReturn(Mono.just(new Product()));

        StepVerifier.create(productService.createProduct(productDto))
                .expectErrorMatches(throwable -> throwable instanceof CustomException &&
                        ((CustomException) throwable).getStatus() == HttpStatus.BAD_REQUEST)
                .verify();

        verify(productRepository).findByName("Product1");
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testDeleteProduct_Success() {
        when(productRepository.findById(1)).thenReturn(Mono.just(new Product()));
        when(productRepository.deleteById(1)).thenReturn(Mono.empty());

        StepVerifier.create(productService.deleteProduct(1))
                .verifyComplete();

        verify(productRepository).findById(1);
        verify(productRepository).deleteById(1);
    }

    @Test
    void testDeleteProduct_NotFound() {
        when(productRepository.findById(1)).thenReturn(Mono.empty());

        StepVerifier.create(productService.deleteProduct(1))
                .expectErrorMatches(throwable -> throwable instanceof CustomException &&
                        ((CustomException) throwable).getStatus() == HttpStatus.NOT_FOUND)
                .verify();

        verify(productRepository).findById(1);
        verify(productRepository, never()).deleteById(anyInt());
    }
}
