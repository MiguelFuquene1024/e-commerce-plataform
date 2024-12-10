package com.plataform.api.infrastructure.repository;

import com.plataform.api.domain.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product,Integer> {

}
