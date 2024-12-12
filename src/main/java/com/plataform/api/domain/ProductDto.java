package com.plataform.api.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
    @NotBlank(message = "name is mandaroty")
    private String name;
    private String description;
    @Min(value = 1, message = "price must be greater than zero")
    private float price;
    private Integer amount;
}
