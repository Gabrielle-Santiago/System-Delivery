package com.gabrielle.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielle.delivery.dto.ProductDTO;
import com.gabrielle.delivery.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
    List<ProductEntity> findByName(String name);

    ProductEntity save(ProductDTO dto);
}
