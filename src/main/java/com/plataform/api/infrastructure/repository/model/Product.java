package com.plataform.api.infrastructure.repository.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="producto")
public class Product {

    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private float price;
    @Column
    private Integer amount;

}
