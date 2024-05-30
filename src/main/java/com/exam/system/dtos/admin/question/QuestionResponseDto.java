package com.exam.system.dtos.admin.question;

import com.exam.system.dtos.admin.option.OptionResponseDto;
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
    private long moduleId;
    private String questionText;
    private String type;
    private Set<OptionResponseDto> options;

    public QuestionResponseDto() {}

    public QuestionResponseDto(Question question) {
        this.id = question.getId();
        this.questionText = question.getQuestionText();
        this.type = question.getQuestionType().toString();
        this.moduleId = question.getModule().getId();
        Set<Option> options = question.getOptions();

        if(options != null) {
            this.options = new HashSet<>();
            for (Option option : options)
                this.options.add(new OptionResponseDto(option));
        }
    }
}
