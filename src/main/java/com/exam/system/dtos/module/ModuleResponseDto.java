package com.exam.system.dtos.module;

import com.exam.system.models.Exam;
import com.exam.system.models.Module;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleResponseDto {
    private long id;
    private String name;
    private String description;
    private long examId;

    public ModuleResponseDto() {
    }

    public  ModuleResponseDto(Module module) {
        this.id = module.getId();
        this.name = module.getName();
        this.description = module.getDescription();
    }
}