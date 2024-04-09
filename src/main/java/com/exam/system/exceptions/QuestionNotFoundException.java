package com.exam.system.exceptions;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException() {
        super("Question didn't exist");
    }
}
