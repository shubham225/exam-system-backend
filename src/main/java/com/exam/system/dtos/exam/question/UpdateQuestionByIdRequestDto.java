package com.exam.system.dtos.exam.question;

import com.exam.system.dtos.BaseDto;
import com.exam.system.enums.QuestionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateQuestionByIdRequestDto extends BaseDto {
    private QuestionStatus status;
    private long answer;
}
