package com.exam.system.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Option extends BaseModel {
    private String optionText;
    @ManyToOne
    private Question question;
    private boolean isAnswer;
}
