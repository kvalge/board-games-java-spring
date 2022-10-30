package com.example.boardgamesjavaspring.domain.product;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductRequest implements Serializable {

    private final String productName;
    private final Float price;
    private final Integer amount;
}
