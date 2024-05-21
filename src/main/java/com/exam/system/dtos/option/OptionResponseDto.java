package com.exam.system.dtos.option;

import com.exam.system.models.Option;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionResponseDto {
    private long id;
    private String optionText;
    private boolean isAnswer;

    public OptionResponseDto() {}

    public OptionResponseDto(Option option) {
        this.id = option.getId();
        this.optionText = option.getOptionText();
        this.isAnswer = option.isAnswer();
    }
}
