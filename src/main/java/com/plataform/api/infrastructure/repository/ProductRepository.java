package com.plataform.api.infrastructure.repository;

import com.plataform.api.domain.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product,Integer> {

    Mono<Product> findByName(String name);

    @Query("SELECT * FROM producto WHERE id <> :id AND name = :name")
    Mono<Product> repeatedProduct(int id, String name);


}
