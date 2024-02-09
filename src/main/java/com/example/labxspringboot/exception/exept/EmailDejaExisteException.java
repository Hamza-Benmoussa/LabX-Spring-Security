package com.example.labxspringboot.exception.exept;

public class EmailDejaExisteException extends RuntimeException {
    public EmailDejaExisteException(String message){
        super(message);
    }
}
