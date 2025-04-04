package com.gabrielle.delivery.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double valueProduct;
    private String describe;

    public ProductEntity(){}

    public ProductEntity(String name, Double valueProduct, String describe){
        this.name = name;
        this.valueProduct = valueProduct;
        this.describe = describe;
    }
}
