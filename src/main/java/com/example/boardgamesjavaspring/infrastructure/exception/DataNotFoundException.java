package com.example.boardgamesjavaspring.infrastructure.exception;

import lombok.Data;

@Data
public class DataNotFoundException extends RuntimeException {

    private String message;

    public DataNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
