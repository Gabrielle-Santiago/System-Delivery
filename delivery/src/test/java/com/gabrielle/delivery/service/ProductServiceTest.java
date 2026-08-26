package com.gabrielle.delivery.service;

import com.gabrielle.delivery.dto.ProductDTO;
import com.gabrielle.delivery.entities.ProductEntity;
import com.gabrielle.delivery.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository repository;

    @InjectMocks
    ProductService service;

    @Test
    void getAllProducts() {
        Double value = 10.0;

        ProductEntity entity = new ProductEntity(
                "name",
                value,
                "describe",
                "imgName"
        );

        List<ProductEntity> expected = List.of(entity);

        when(repository.findAll()).thenReturn(expected);

        List<ProductEntity> result = service.getAllProducts();

        assertEquals(expected, result);
    }

    @Test
    void addProduct() {
        Double value = 10.0;

        ProductEntity product = new ProductEntity(
                "name",
                value,
                "describe",
                "imgName"
        );

        when(repository.save(product)).thenReturn(product);

        ProductEntity result = service.addProduct(product);

        assertEquals(product, result);
    }

    @Test
    void deleteProduct() {
        Long id = 1L;

        service.deleteProduct(id);
        verify(repository).deleteById(id);
    }

    @Test
    void updateProduct() {
        Long id = 1L;

        ProductEntity product = new ProductEntity(
                "Old name",
                10.0,
                "Old description",
                "Old Image"
        );

        ProductDTO dto = new ProductDTO(
                "New description",
                "New name",
                20.0,
                "new Image"
        );

        when(repository.findById(id)).thenReturn(Optional.of(product));
        when(repository.save(product)).thenReturn(product);

        ProductEntity result = service.updateProduct(id, dto);

        assertEquals("New name", result.getName());
        assertEquals(20.0, result.getValueProduct());
        assertEquals("New description", result.getDescribe());

        verify(repository).findById(id);
        verify(repository).save(product);
    }
}