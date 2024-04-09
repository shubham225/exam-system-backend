package com.exam.system.exceptions;

public class OptionNotFoundException extends RuntimeException{
    public OptionNotFoundException() {
        super("Option didn't exist");
    }
}
