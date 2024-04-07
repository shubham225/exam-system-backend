package com.exam.system.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Option extends BaseModel {
    private String description;
    private Question question;
    private boolean isAnswer;
}
