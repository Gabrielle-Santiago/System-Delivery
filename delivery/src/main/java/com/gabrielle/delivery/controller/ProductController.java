package com.gabrielle.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gabrielle.delivery.dto.ProductDTO;
import com.gabrielle.delivery.entities.ProductEntity;
import com.gabrielle.delivery.service.ProductService;

@RestController
@RequestMapping("admin")
public class ProductController {
    
    @Autowired
    private ProductService service;

    @GetMapping("/home")
    public List<ProductEntity> home(){
        return service.getAllProducts();
    }

    @PostMapping("/add")
    public ProductEntity addProduct(@RequestBody ProductEntity product){
        return service.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public ProductEntity patchProduct(
        @PathVariable Long id,
        @RequestBody ProductDTO dto
        ){ return service.updateProduct(id, dto); }
}
