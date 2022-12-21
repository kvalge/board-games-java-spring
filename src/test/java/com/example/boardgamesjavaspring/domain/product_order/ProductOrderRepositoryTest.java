package com.example.boardgamesjavaspring.domain.product_order;

import com.example.boardgamesjavaspring.domain.product.Product;
import com.example.boardgamesjavaspring.domain.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductOrderRepositoryTest {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByCustomerIgnoreCaseAndId() {
/*        ProductOrder productOrderEntity = getProductOrderEntity();
        String customer = productOrderEntity.getCustomer();
        Long id = productOrderEntity.getId();
        productOrderRepository.save(productOrderEntity);

        ProductOrder byCustomerAndId = productOrderRepository.findByCustomerIgnoreCaseAndId(customer, id);

        assertNotNull(byCustomerAndId);

        productOrderRepository.delete(productOrderEntity);
        productRepository.delete(getProductEntity());*/
    }

    @Test
    void findOrdersByCustomerName() {
        ProductOrder productOrderEntity = getProductOrderEntity();
        ProductOrder productOrderEntity2 = getProductOrderEntity2();
        String customer = productOrderEntity.getCustomer();
        productOrderRepository.save(productOrderEntity);
        productOrderRepository.save(productOrderEntity2);

        List<ProductOrder> ordersByCustomerName = productOrderRepository.findOrdersByCustomerName(customer);
        assertEquals(ordersByCustomerName.size(), 2);

        productOrderRepository.deleteById(ordersByCustomerName.get(0).getId());
        productOrderRepository.deleteById(ordersByCustomerName.get(1).getId());
        productRepository.deleteByProductNameAllIgnoreCase(productOrderEntity.getProduct().getProductName());

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

        productOrder.setCustomer("Customer");

        Product product = getProductEntity();
        productOrder.setProduct(product);
        productOrder.setQuantity(1);

        Integer quantity = productOrder.getQuantity();
        float price = getProductEntity().getPrice();
        float totalPrice = quantity * price;
        productOrder.setOrderTotalPrice(totalPrice);
        productOrder.setOrderDate(LocalDate.ofEpochDay(7 - 12 - 2022));

        LocalDate orderDate = productOrder.getOrderDate();
        productOrder.setDeadline(orderDate.plusDays(4));
        productOrder.setStatus("In process");

        return productOrder;
    }

    /**
     * Hard coded second version of product order entity.
     */
    private ProductOrder getProductOrderEntity2() {
        ProductOrder productOrder = new ProductOrder();

        productOrder.setCustomer("Customer");

        Product product = getProductEntity();
        productOrder.setProduct(product);
        productOrder.setQuantity(2);

        Integer quantity = productOrder.getQuantity();
        float price = getProductEntity().getPrice();
        float totalPrice = quantity * price;
        productOrder.setOrderTotalPrice(totalPrice);
        productOrder.setOrderDate(LocalDate.ofEpochDay(8 - 12 - 2022));

        LocalDate orderDate = productOrder.getOrderDate();
        productOrder.setDeadline(orderDate.plusDays(4));
        productOrder.setStatus("In process");

        return productOrder;
    }
}