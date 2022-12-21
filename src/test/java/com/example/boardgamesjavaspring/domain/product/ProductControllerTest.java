package com.example.boardgamesjavaspring.domain.product;

import com.example.boardgamesjavaspring.domain.product_order.ProductOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductOrderRepository productOrderRepository;


    /**
     * Tests equality between hard coded product request name and product name saved to database via
     * addNewProduct method.
     */
    @Test
    void addNewProduct() {
        ProductRequest request = new ProductRequest("Game", 11.11F, 111);
        String productName = request.getProductName();

        productController.addNewProduct(request);

        Product byProductName = productRepository.findByProductNameIgnoreCase(productName);
        String databaseProductName = byProductName.getProductName();

        assertEquals(databaseProductName, productName);

        productRepository.deleteByProductNameAllIgnoreCase(databaseProductName);
    }

    /**
     * Tests if products received from database via method give not null value when requested from database
     * via repository method to find product.
     */
    @Test
    void getAllProducts() {
        List<ProductDto> allProducts = productController.getAllProducts();

        for (ProductDto productDto : allProducts) {
            String productName = productDto.getProductName();
            Product byProductName = productRepository.findByProductNameIgnoreCase(productName);

            assertNotNull(byProductName);
        }
    }

    /**
     * Tests equality between hard coded product entity name saved to database using repository save method and
     * product name returned via getProductByName method.
     */
    @Test
    void getProductByName() {
        Product productEntity = getProductEntity();
        String productName = productEntity.getProductName();
        productRepository.save(productEntity);

        ProductDto productByName = productController.getProductByName(productName);
        String actualProductName = productByName.getProductName();

        assertEquals(productName, actualProductName);
    }

    @Test
    void updateProductByName() {
    }

    @Test
    void updateAmountByName() {
    }

    @Test
    void deleteProductByName() {
    }

    @Test
    void deleteProductById() {
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
}