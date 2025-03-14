package com.gabrielle.delivery.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValueProduct() {
        return valueProduct;
    }

    public void setValueProduct(Double valueProduct) {
        this.valueProduct = valueProduct;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Long getId() {
        return id;
    }
    
}
