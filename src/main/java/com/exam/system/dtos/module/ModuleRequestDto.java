package com.exam.system.dtos.module;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleRequestDto {
    private long examId;
    private String name;
    private String description;
}
