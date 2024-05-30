package com.exam.system.models;

import com.exam.system.enums.QuestionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class StudentQuestion extends BaseModel {
    @ManyToOne
    private Question question;
    private int sequence;
    @ManyToOne
    private StudentModule module;
    @ManyToOne
    private Option answer;
    private QuestionStatus status;

    public StudentQuestion() {
        this.status = QuestionStatus.NOT_VISITED;
    }

    public StudentQuestion(StudentModule module, Question question, int sequence) {
        this.question = question;
        this.module = module;
        this.sequence = sequence;
        this.status = QuestionStatus.NOT_VISITED;
    }
}
