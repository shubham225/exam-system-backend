package com.exam.system.models;

import com.exam.system.enums.ExamStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class UserExam extends BaseModel {
    @ManyToOne
    private Exam exam;
    @ManyToOne
    private User user;
    private Date createdOn;
    private Date endedOn;
    private ExamStatus status;
}
