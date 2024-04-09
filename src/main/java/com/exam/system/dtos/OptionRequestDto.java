package com.exam.system.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionRequestDto {
    private int questionId;
    private String optionText;
    private boolean isAnswer;
}
