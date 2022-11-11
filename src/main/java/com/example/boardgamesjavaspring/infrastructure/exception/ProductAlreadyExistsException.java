package com.example.boardgamesjavaspring.infrastructure.exception;

import lombok.Data;

@Data
public class ProductAlreadyExistsException extends RuntimeException {

    private String message;

    public ProductAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}