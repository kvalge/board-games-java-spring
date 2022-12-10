package com.example.boardgamesjavaspring.domain.product_order;

import com.example.boardgamesjavaspring.domain.product.Product;
import com.example.boardgamesjavaspring.domain.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

class ProductOrderRepositoryTest {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByCustomerIgnoreCaseAndId() {
        ProductOrder productOrderEntity = getProductOrderEntity();
        String customer = productOrderEntity.getCustomer();
        Long id = productOrderEntity.getId();
        productOrderRepository.save(productOrderEntity);

        ProductOrder byCustomerAndId = productOrderRepository.findByCustomerIgnoreCaseAndId(customer, id);

        assertNotNull(byCustomerAndId);

        productOrderRepository.delete(getProductOrderEntity());
        productRepository.delete(getProductEntity());
    }

    @Test
    void findOrdersByCustomerName() {
    }

    @Test
    void findOrdersByProductName() {
    }

    /**
     * Hard coded product entity.
     */
    private Product getProductEntity() {
        Product product = new Product();
        product.setProductName("Game");
        product.setPrice(10.00F);
        product.setAmount(1);
        productRepository.save(product);
        return product;
    }

    /**
     * Hard coded product order entity.
     */
    private ProductOrder getProductOrderEntity() {
        ProductOrder productOrder = new ProductOrder();

        productOrder.setId(100L);
        productOrder.setCustomer("Customer");

        Product product = getProductEntity();
        productOrder.setProduct(product);
        productOrder.setQuantity(1);

        Integer quantity = productOrder.getQuantity();
        float price = getProductEntity().getPrice();
        float totalPrice = quantity * price;
        productOrder.setOrderTotalPrice(totalPrice);
        productOrder.setOrderDate(LocalDate.ofEpochDay(07-12-2022));

        LocalDate orderDate = productOrder.getOrderDate();
        productOrder.setDeadline(orderDate.plusDays(4));
        productOrder.setStatus("In process");

        return productOrder;
    }
}