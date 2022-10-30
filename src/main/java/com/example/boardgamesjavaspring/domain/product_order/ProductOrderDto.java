package com.example.boardgamesjavaspring.domain.product_order;

import com.example.boardgamesjavaspring.domain.product.ProductDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link ProductOrder} entity
 */
@Data
public class ProductOrderDto implements Serializable {
    private final Long id;
    @Size(max = 250)
    @NotNull
    private final String customer;
    private final ProductDto product;
    @NotNull
    private final Integer quantity;
    @NotNull
    private final Float orderTotalPrice;
    @NotNull
    private final LocalDate orderDate;
    @NotNull
    private final LocalDate deadline;
    @Size(max = 25)
    @NotNull
    private final String status;
}