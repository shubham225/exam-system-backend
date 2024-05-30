package com.exam.system.dtos.exam.question;

import com.exam.system.dtos.BaseDto;
import com.exam.system.dtos.admin.option.OptionResponseDto;
import com.exam.system.dtos.exam.option.StudentOptionResponseDto;
import com.exam.system.enums.QuestionStatus;
import com.exam.system.models.Option;
import com.exam.system.models.StudentQuestion;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class StudentQuestionResponseDto extends BaseDto {
    private long moduleId;
    private String questionText;
    private QuestionStatus status;
    private long answer;
    private Set<StudentOptionResponseDto> options;

    public StudentQuestionResponseDto() {}

    public StudentQuestionResponseDto(StudentQuestion studentQuestion) {
        this.id = studentQuestion.getId();
        this.questionText = studentQuestion.getQuestion().getQuestionText();
        this.status = studentQuestion.getStatus();
        this.moduleId = studentQuestion.getQuestion().getModule().getId();
        this.answer = 0;

        if(studentQuestion.getAnswer() != null)
            this.answer = studentQuestion.getAnswer().getId();

        Set<Option> options = studentQuestion.getQuestion().getOptions();

        if(options != null) {
            this.options = new HashSet<>();
            for (Option option : options)
                this.options.add(new StudentOptionResponseDto(option));
        }
    }
}
