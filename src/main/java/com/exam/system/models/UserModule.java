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
public class UserModule extends BaseModel {
    @ManyToOne
    private Module module;
    @ManyToOne
    private UserExam userExam;
    private Date createdOn;
    private Date endedOn;
    private ExamStatus status;
}
