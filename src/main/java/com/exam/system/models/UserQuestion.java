package com.exam.system.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserQuestion extends BaseModel {
    @ManyToOne
    private Question question;
    @ManyToOne
    private UserModule userModule;
    @ManyToOne
    private Option selectedAnswer;
    private boolean marked;
    private boolean answered;
}
