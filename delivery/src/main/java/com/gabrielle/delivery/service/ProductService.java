package com.gabrielle.delivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielle.delivery.dto.ProductDTO;
import com.gabrielle.delivery.entities.ProductEntity;
import com.gabrielle.delivery.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductEntity> getAllProducts(){
        return repository.findAll();
    }

    public ProductEntity addProduct(ProductEntity product){  
        return repository.save(product);
    }

    public void deleteProduct(Long id){
        repository.deleteById(id);
    }

    public ProductEntity updateProduct(Long id, ProductDTO dto) {
        ProductEntity product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (dto.getName() != null) {
            product.setName(dto.getName());
        }
        if(dto.getValueProduct() != 0) {
            product.setValueProduct(dto.getValueProduct());
        }
        if (dto.getDescribe() != null) {
            product.setDescribe(dto.getDescribe());
        }

        return repository.save(product);
    }   
}
