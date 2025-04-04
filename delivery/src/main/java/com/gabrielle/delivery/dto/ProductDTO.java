package com.gabrielle.delivery.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    private String name;
    private Double valueProduct;
    private String describe;

    public ProductDTO(String describe, String name, Double valueProduct) {
        this.describe = describe;
        this.name = name;
        this.valueProduct = valueProduct;
    }
}
