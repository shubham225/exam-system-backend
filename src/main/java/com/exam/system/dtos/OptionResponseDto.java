package com.exam.system.dtos;

import com.exam.system.models.Option;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionResponseDto {
    private long id;
    private String optionText;
    private long questionId;

    public OptionResponseDto() {}

    public OptionResponseDto(Option option) {
        this.id = option.getId();
        this.optionText = option.getOptionText();
        this.questionId = option.getQuestion().getId();
    }
}
