package com.example.boardgamesjavaspring.domain.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Tests if findByProductNameIgnoreCase method returns value after hard coded event entity is saved via repository save method.
     */
    @Test
    void findByProductNameIgnoreCase() {
        Product productEntity = getProductEntity();
        saveProductEntity(productEntity);

        String productName = productEntity.getProductName();
        Product byProductName = productRepository.findByProductNameIgnoreCase(productName);

        assertNotNull(byProductName);

        productRepository.deleteByProductNameAllIgnoreCase(productName);
    }

    /**
     * Tests if hard coded event value saved via repository save method is null after using delete method.
     */
    @Test
    void deleteByProductNameAllIgnoreCase() {
        Product productEntity = getProductEntity();
        saveProductEntity(productEntity);

        String productName = productEntity.getProductName();
        productRepository.deleteByProductNameAllIgnoreCase(productName);

        assertNull(productRepository.findByProductNameIgnoreCase(productName));
    }

    /**
     * Hard coded product entity.
     */
    private static Product getProductEntity() {
        Product product = new Product();
        product.setProductName("Game");
        product.setPrice(10.00F);
        product.setAmount(1);
        return product;
    }

    private void saveProductEntity(Product productEntity) {
        productRepository.save(productEntity);
    }
}