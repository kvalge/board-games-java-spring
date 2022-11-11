package com.example.boardgamesjavaspring.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductNameIgnoreCase(String productName);

    @Transactional
    @Modifying
    @Query("delete from Product p where upper(p.productName) = upper(?1)")
    int deleteByProductNameAllIgnoreCase(String productName);
}