package com.exam.system.exceptions;

public class ExamNotFoundException extends RuntimeException{
    public ExamNotFoundException() {
        super("Exam didn't exist");
    }
}
