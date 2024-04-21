package com.exam.system.dtos.question;

import com.exam.system.enums.QuestionType;
import com.exam.system.models.Question;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequestDto {
    private long moduleId;
    private String questionText;
    private int type;
}
