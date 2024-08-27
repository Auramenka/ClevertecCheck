package org.clevertec.checkcrud.repository;

import org.clevertec.checkcrud.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryImplTest {

    private final ProductRepository productRepository = new ProductRepositoryImpl();

    @Test
    void testSave() {
        Product product = new Product();
        product.setDescription("Strawberry");
        product.setPrice(15.78);
        product.setQuantity(10);
        product.setWholesale(true);

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct);
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setId(12);

        productRepository.deleteById(product.getId());
    }

    @Test
    void testGetById() {
        Product product = new Product();
        product.setId(7);
        product.setDescription("Milk");
        product.setPrice(3.21);
        product.setQuantity(2);
        product.setWholesale(true);

        Product result = productRepository.getById(product.getId());

        assertNotNull(result);
        assertEquals(product.getDescription(), result.getDescription());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getQuantity(), result.getQuantity());
        assertEquals(product.isWholesale(), result.isWholesale());
    }

    @Test
    void testUpdate() {
        Product product = new Product();
        product.setId(6);
        product.setDescription("Milk");
        product.setPrice(7.90);
        product.setQuantity(3);
        product.setWholesale(false);

        productRepository.update(product);

        Product updatedProduct = productRepository.getById(product.getId());

        assertNotNull(updatedProduct);
        assertEquals(product.getDescription(), updatedProduct.getDescription());
        assertEquals(product.getPrice(), updatedProduct.getPrice());
        assertEquals(product.getQuantity(), updatedProduct.getQuantity());
        assertEquals(product.isWholesale(), updatedProduct.isWholesale());
    }
}