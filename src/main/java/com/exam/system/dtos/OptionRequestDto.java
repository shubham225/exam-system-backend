package com.exam.system.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionRequestDto {
    private long questionId;
    private String optionText;
    private boolean isAnswer;
}
