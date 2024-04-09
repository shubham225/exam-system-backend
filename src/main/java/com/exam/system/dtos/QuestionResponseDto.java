package com.exam.system.dtos;

import com.exam.system.enums.QuestionType;
import com.exam.system.models.Option;
import com.exam.system.models.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class QuestionResponseDto {
    private long id;
    private String questionText;
    private QuestionType type;
    private Set<OptionResponseDto> options;
    private long moduleId;

    public QuestionResponseDto() {}

    public QuestionResponseDto(Question question) {
        this.id = question.getId();
        this.questionText = question.getQuestionText();
        this.type = question.getQuestionType();
        this.moduleId = question.getModule().getId();
        Set<Option> options = question.getOptions();
        if(options != null) {
            this.options = new HashSet<>();
            for (Option option : options)
                this.options.add(new OptionResponseDto(option));
        }
    }
}
