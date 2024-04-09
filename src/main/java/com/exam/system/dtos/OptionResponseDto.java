package com.exam.system.dtos;

import com.exam.system.models.Option;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionResponseDto {
    private long questionId;
    private long id;
    private String optionText;
    public OptionResponseDto() {}
    public OptionResponseDto(Option option) {
        this.questionId = option.getQuestion().getId();
        this.id = option.getId();
        this.optionText = option.getOptionText();
    }
}
