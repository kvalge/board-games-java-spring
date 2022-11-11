package com.example.boardgamesjavaspring.domain.product_order;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ProductOrderResponse implements Serializable {

    private final Long id;
    private final String customer;
    private final String productName;
    private final Float price;
    private final Float orderTotalPrice;
    private final Integer quantity;
    private final LocalDate orderDate;
    private final LocalDate deadline;
    private final String status;
}
