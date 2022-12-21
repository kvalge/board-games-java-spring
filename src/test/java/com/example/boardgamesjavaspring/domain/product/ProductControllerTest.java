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
        ProductRequest request = getProductRequest();
        String productName = request.getProductName();

        productController.addNewProduct(request);

        Product byProductName = productRepository.findByProductNameIgnoreCase(productName);
        String databaseProductName = byProductName.getProductName();

        assertEquals(databaseProductName, productName);

        deleteProduct(productName);
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
        saveProductEntity(productEntity);

        ProductDto productByName = productController.getProductByName(productName);
        String actualProductName = productByName.getProductName();

        assertEquals(productName, actualProductName);

        deleteProduct(productName);
    }

    /**
     * Tests if hard coded product entity value saved to database is null after using update method.
     * Tests equality between hard coded product request properties saved to database via update
     * method and product properties received by product repository find product method.
     */
    @Test
    void updateProductByName() {
        Product productEntity = getProductEntity();
        String productName = productEntity.getProductName();
        saveProductEntity(productEntity);

        ProductRequest request = getProductRequest();

        productController.updateProductByName(productName, request);

        Product byProductName = productRepository.findByProductNameIgnoreCase(request.getProductName());

        assertNull(productRepository.findByProductNameIgnoreCase(productName));
        assertEquals(request.getProductName(), byProductName.getProductName());
        assertEquals(request.getPrice(), byProductName.getPrice());
        assertEquals(request.getAmount(), byProductName.getAmount());

        deleteProduct(productName);
        deleteProduct(request.getProductName());
    }

    /**
     * Tests if hard coded product entity amount property saved to database via repository method is not
     * equal to new amount saved to database via update method.
     */
    @Test
    void updateAmountByName() {
        Product productEntity = getProductEntity();
        String productName = productEntity.getProductName();
        saveProductEntity(productEntity);

        productController.updateAmountByName(productName, 3);

        Integer amount = productRepository.findByProductNameIgnoreCase(productName).getAmount();

        assertNotEquals(productEntity.getAmount(), amount);

        deleteProduct(productName);
    }

    /**
     * Tests if hard coded product entity saved to database via repository method is null after using
     * controller delete by product name method.
     */
    @Test
    void deleteProductByName() {
        Product productEntity = getProductEntity();
        String productName = productEntity.getProductName();
        saveProductEntity(productEntity);

        productController.deleteProductByName(productName);

        assertNull(productRepository.findByProductNameIgnoreCase(productName));
    }

    /**
     * Tests if hard coded product entity saved to database via repository method is null after using
     * controller delete by product id method.
     */
    @Test
    void deleteProductById() {
        Product productEntity = getProductEntity();
        String productName = productEntity.getProductName();
        saveProductEntity(productEntity);

        Long id = productRepository.findByProductNameIgnoreCase(productName).getId();

        productController.deleteProductById(id);

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

    private static ProductRequest getProductRequest() {
        return new ProductRequest("Game2", 11.11F, 111);
    }

    private void saveProductEntity(Product productEntity) {
        productRepository.save(productEntity);
    }

    private void deleteProduct(String productName) {
        productRepository.deleteByProductNameAllIgnoreCase(productName);
    }
}