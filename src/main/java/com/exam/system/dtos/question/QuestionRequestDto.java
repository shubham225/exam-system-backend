package com.exam.system.dtos.question;

import com.exam.system.dtos.option.OptionRequestDto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequestDto {
    private long id;
    private long moduleId;
    private String questionText;
    private String type;
    private Set<OptionRequestDto> options;
}
