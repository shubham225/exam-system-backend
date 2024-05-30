package com.exam.system.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class StudentModule extends BaseModel {
    @ManyToOne
    private Module module;
    @ManyToOne
    private StudentExam exam;
    private int sequence;

    public StudentModule() {}
    public StudentModule(StudentExam exam, Module module, int sequence) {
        this.module = module;
        this.exam = exam;
        this.sequence = sequence;
    }
}
