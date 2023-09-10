package com.hq.springbootchatbot.exception;

public class ModelNotSupportException extends RuntimeException {
    public ModelNotSupportException(String message) {
        super(message);
    }
}
