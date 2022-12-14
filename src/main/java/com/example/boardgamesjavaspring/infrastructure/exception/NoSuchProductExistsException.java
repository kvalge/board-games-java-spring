package com.example.boardgamesjavaspring.infrastructure.exception;

import lombok.Data;

@Data
public class NoSuchProductExistsException extends RuntimeException {

    private String message;

    public NoSuchProductExistsException(String message) {
        super(message);
        this.message = message;
    }
}
