package com.exam.system.dtos.exam.option;

import com.exam.system.dtos.BaseDto;
import com.exam.system.models.Option;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentOptionResponseDto extends BaseDto {
    private String optionText;

    public StudentOptionResponseDto() {}

    public StudentOptionResponseDto(Option option) {
        this.id = option.getId();
        this.optionText = option.getOptionText();
    }
}
