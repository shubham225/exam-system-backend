package com.exam.system.dtos;

import com.exam.system.enums.QuestionType;
import com.exam.system.models.Question;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponseDto {
    private long moduleId;
    private String questionText;
    private QuestionType type;

    public QuestionResponseDto() {}

    public QuestionResponseDto(Question question) {
        this.moduleId = question.getModule().getId();
        this.questionText = question.getQuestionText();
        this.type = question.getQuestionType();
    }
}
