package com.exam.system.dtos.admin.option;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionRequestDto {
    private int id;
    private String optionText;
    private boolean isAnswer;
}
