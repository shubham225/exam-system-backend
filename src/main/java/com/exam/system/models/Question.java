package com.exam.system.models;

import com.exam.system.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question extends BaseModel {
    private String description;
    private Module module;
    private QuestionType questionType;
}
