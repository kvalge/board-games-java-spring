package com.example.boardgamesjavaspring.domain.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Tests equality between hard coded product request name and product name saved to database via
     * addNewProduct method.
     */
    @Test
    void addNewProduct() {
        ProductRequest request = new ProductRequest("Game", 10.00F, 1);

        productService.addNewProduct(request);

        String productName = request.getProductName();
        String byProductName = productRepository.findByProductNameIgnoreCase(productName).getProductName();

        assertEquals(productName, byProductName);

        delete(productName);
    }

    /**
     * Tests if hard coded product entity name saved to database using repository save method is equal to
     * product dto name returned by getAllProducts method.
     */
    @Test
    void getAllProducts() {
        String productName = savedProductName();

        List<ProductDto> allProducts = productService.getAllProducts();
        for (ProductDto product : allProducts) {
            String pdtName = product.getProductName();
            if (pdtName.equals(productName)) {
                assertEquals(productName, pdtName);
            }
        }
        delete(productName);
    }

    /**
     * Tests equality between hard coded product entity name saved to database using repository save method and
     * product name returned via getProductByName method.
     */
    @Test
    void getProductByName() {
        String productName = savedProductName();

        String productByName = productService.getProductByName(productName).getProductName();

        String byProductName = productRepository.findByProductNameIgnoreCase(productName).getProductName();

        assertEquals(byProductName, productByName);

        delete(productName);
    }

    /**
     * Tests equality between hard coded product request and updated product saved to database
     * via updateProductByName method.
     */
    @Test
    void updateProductByName() {
        String productName = savedProductName();

        ProductRequest newRequest = new ProductRequest("Game2", 12.00F, 2);
        String requestName = newRequest.getProductName();
        Float requestPrice = newRequest.getPrice();
        Integer requestAmount = newRequest.getAmount();

        productService.updateProductByName(productName, newRequest);

        String byName = productRepository.findByProductNameIgnoreCase(requestName).getProductName();
        Float price = productRepository.findByProductNameIgnoreCase(requestName).getPrice();
        Integer amount = productRepository.findByProductNameIgnoreCase(requestName).getAmount();

        assertEquals(requestName, byName);
        assertEquals(requestPrice, price);
        assertEquals(requestAmount, amount);

        delete(productName);
        delete(requestName);
    }

    /**
     * Tests equality between hard coded product amount and updated product amount saved to database
     * via updateAmountByName method.
     */
    @Test
    void updateAmountByName() {
        String productName = savedProductName();

        int newAmount = 5;
        productService.updateAmountByName(productName, newAmount);

        Integer amount = productRepository.findByProductNameIgnoreCase(productName).getAmount();

        assertEquals(newAmount, amount);

        delete(productName);
    }

    /**
     * Tests if hard coded product entity value is null after using deleteProductByName method.
     */
    @Test
    void deleteProductByName() {
        String productName = savedProductName();

        productService.deleteProductByName(productName);

        Product byProductName = productRepository.findByProductNameIgnoreCase(productName);

        assertNull(byProductName);
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

    private String savedProductName() {
        Product productEntity = getProductEntity();
        saveProductEntity(productEntity);
        return productEntity.getProductName();
    }

    private void delete(String productName) {
        productRepository.deleteByProductNameAllIgnoreCase(productName);
    }
}
