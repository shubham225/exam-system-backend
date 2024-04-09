package com.exam.system.dtos;

import com.exam.system.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponseDto {
    private int moduleId;
    private String questionText;
    private QuestionType type;
}
