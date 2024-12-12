package com.plataform.api.infrastructure.handler;

/*import com.plataform.api.domain.ProductDto;
import com.plataform.api.infrastructure.repository.model.Product;
import com.plataform.api.infrastructure.adapter.ProductServiceImpl;
import com.plataform.api.infrastructure.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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

    private final ObjectValidator objectValidator;


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
        Mono<ProductDto> productDtoMono = request.bodyToMono(ProductDto.class).doOnNext(objectValidator::validate);
        return productDtoMono.flatMap(productDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productService.createProduct(productDto), Product.class));
    }

    public Mono<ServerResponse> updateProduct(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        Mono<ProductDto> productDtoMono = request.bodyToMono(ProductDto.class).doOnNext(objectValidator::validate);;
        return productDtoMono.flatMap(productDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productService.updateProduct(id,productDto), Product.class));
    }

    public Mono<ServerResponse> deteleProduct(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(productService.deleteProduct(id), Product.class);
    }
}*/
