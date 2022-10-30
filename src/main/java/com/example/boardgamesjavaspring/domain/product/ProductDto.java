package com.example.boardgamesjavaspring.domain.product;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Product} entity
 */
@Data
public class ProductDto implements Serializable {
    private final Long id;
    @Size(max = 250)
    @NotNull
    private final String productName;
    @NotNull
    private final Float price;
    @NotNull
    private final Integer amount;
}