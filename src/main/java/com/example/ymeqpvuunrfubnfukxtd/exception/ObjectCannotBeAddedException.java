package com.example.ymeqpvuunrfubnfukxtd.exception;

// Exception кидается, когда объект не может быть добавлен из-за неправильности данных
public class ObjectCannotBeAddedException extends RuntimeException {
    public ObjectCannotBeAddedException(String message) {
        super(message);
    }
}

