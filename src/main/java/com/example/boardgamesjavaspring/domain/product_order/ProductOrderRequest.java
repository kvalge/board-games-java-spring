package com.example.boardgamesjavaspring.domain.product_order;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ProductOrderRequest implements Serializable {

    private final String customer;
    private final String productName;
    private final Integer quantity;
    private final LocalDate orderDate;
}
