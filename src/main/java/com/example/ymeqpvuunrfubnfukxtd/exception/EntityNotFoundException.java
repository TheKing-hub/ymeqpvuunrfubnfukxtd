package com.example.ymeqpvuunrfubnfukxtd.exception;

// Exception кидается, если сущности нет в БД
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}

