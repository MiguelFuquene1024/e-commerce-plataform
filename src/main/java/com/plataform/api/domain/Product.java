package com.plataform.api.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class Product {

    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Long price;
    @Column
    private int amount;

}
