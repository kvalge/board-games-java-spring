package com.example.boardgamesjavaspring.domain.product_order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

    ProductOrder findByCustomerIgnoreCaseAndId(String customer, Long id);

    @Query("select p from ProductOrder p where upper(p.customer) = upper(?1) order by p.deadline DESC")
    List<ProductOrder> findOrdersByCustomerName(String customer);

    @Query("select p from ProductOrder p where upper(p.product.productName) = upper(?1)")
    List<ProductOrder> findOrdersByProductName(String productName);
}