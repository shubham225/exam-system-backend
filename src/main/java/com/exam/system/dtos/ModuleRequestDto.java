package com.exam.system.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleRequestDto {
    private int examId;
    private String name;
    private String description;
}
