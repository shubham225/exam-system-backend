package com.exam.system.exceptions;

public class ExamAlreadyAssignedException extends RuntimeException{
    public ExamAlreadyAssignedException() {
        super("Exam Already Assigned to this User");
    }

}
