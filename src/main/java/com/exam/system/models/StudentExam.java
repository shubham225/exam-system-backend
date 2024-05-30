package com.exam.system.models;

import com.exam.system.enums.ExamStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class StudentExam extends BaseModel{
    @ManyToOne
    private Exam exam;
    private Date startTime;
    private Date endTime;
    private ExamStatus status;
    @ManyToOne
    private User student;
    private int score;
    private int maxScore;

    public StudentExam() {
        this.status = ExamStatus.PENDING;
        this.score = 0;
    }

    public StudentExam(User student, Exam exam) {
        this.exam = exam;
        this.student = student;
        this.status = ExamStatus.PENDING;
        this.score = 0;
    }
}
