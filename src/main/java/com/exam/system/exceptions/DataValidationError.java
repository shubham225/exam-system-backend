package com.exam.system.exceptions;

public class DataValidationError extends RuntimeException{
    public DataValidationError(String message) {
        super(message);
    }
}
