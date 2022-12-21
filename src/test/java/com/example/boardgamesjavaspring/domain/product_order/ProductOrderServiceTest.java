package com.example.boardgamesjavaspring.domain.product_order;

import com.example.boardgamesjavaspring.domain.product.Product;
import com.example.boardgamesjavaspring.domain.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductOrderServiceTest {

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Resource
    private ProductRepository productRepository;

    /**
     * Tests equality between hard coded product order request id and product order id saved to database via
     * addNewProductOrder method.
     */
    @Test
    void addNewProductOrder() {
        ProductOrderRequest request = getProductOrderRequest();
        productRepository.save(getProductEntity());

        productOrderService.addNewProductOrder(request);

        String customer = request.getCustomer();
        List<ProductOrder> ordersByCustomerName = productOrderRepository.findOrdersByCustomerName(customer);

        assertNotNull(ordersByCustomerName.get(0));

        productOrderRepository.deleteById(ordersByCustomerName.get(0).getId());
        productRepository.deleteByProductNameAllIgnoreCase(request.getProductName());
    }

    @Test
    void getOrdersByCustomerName() {
    }

    @Test
    void getResponseByCustomerName() {
    }

    @Test
    void updateAmount() {
    }

    @Test
    void updateStatus() {
    }

    @Test
    void delete() {
    }

    private Product getProductEntity() {
        Product product = new Product();
        product.setProductName("Game");
        product.setPrice(10.00F);
        product.setAmount(1);
        productRepository.save(product);
        return product;
    }

    private ProductOrderRequest getProductOrderRequest() {
        String customer = "Customer";
        String productName = "Game";
        int quantity = 1;
        LocalDate date = LocalDate.ofEpochDay(8 - 12 - 2022);

        return new ProductOrderRequest(customer, productName, quantity, date);
    }
}