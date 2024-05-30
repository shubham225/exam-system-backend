package com.exam.system.dtos.admin.module;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleRequestDto {
    private long examId;
    private String moduleName;
    private String description;
}
