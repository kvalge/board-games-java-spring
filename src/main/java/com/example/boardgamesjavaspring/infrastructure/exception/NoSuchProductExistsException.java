package com.example.boardgamesjavaspring.infrastructure.exception;

public class NoSuchProductExistsException extends RuntimeException {

    private String message;

    public NoSuchProductExistsException(String message) {
        super(message);
        this.message = message;
    }
}
