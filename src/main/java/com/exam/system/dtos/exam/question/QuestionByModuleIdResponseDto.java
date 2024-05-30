package com.exam.system.dtos.exam.question;

import com.exam.system.dtos.BaseDto;
import com.exam.system.dtos.admin.option.OptionResponseDto;
import com.exam.system.dtos.exam.option.StudentOptionResponseDto;
import com.exam.system.enums.QuestionStatus;
import com.exam.system.models.Option;
import com.exam.system.models.Question;
import com.exam.system.models.StudentQuestion;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class QuestionByModuleIdResponseDto extends BaseDto {
    private long moduleId;
    private int sequence;
    private QuestionStatus status;

    public QuestionByModuleIdResponseDto() {}

    public QuestionByModuleIdResponseDto(StudentQuestion studentQuestion) {
        this.id = studentQuestion.getId();
        this.moduleId = studentQuestion.getModule().getId();
        this.sequence = studentQuestion.getSequence();
        this.status = studentQuestion.getStatus();
    }
}
