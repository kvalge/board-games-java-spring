package com.example.boardgamesjavaspring.domain.product_order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    @Query("select p from ProductOrder p where upper(p.customer) = upper(?1) order by p.deadline DESC")
    List<ProductOrder> findOrdersByCustomerName(String customer);



}