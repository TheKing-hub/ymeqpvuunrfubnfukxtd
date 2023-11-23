package com.example.ymeqpvuunrfubnfukxtd.exception;

// Exception кидается, когда данные неверно заполнены для объекта
public class IncorrectCallObject extends RuntimeException {
    public IncorrectCallObject(String message) {
        super(message);
    }
}
