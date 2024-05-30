package com.exam.system.dtos.exam.module;

import com.exam.system.dtos.BaseDto;
import com.exam.system.models.StudentModule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleByExamIdResponseDto extends BaseDto {
    private long examId;
    private int sequence;
    private String moduleName;
    private String description;

    public ModuleByExamIdResponseDto() { }

    public ModuleByExamIdResponseDto(StudentModule studentModule) {
        this.id = studentModule.getId();
        this.examId = studentModule.getExam().getId();
        this.sequence = studentModule.getSequence();
        this.moduleName = studentModule.getModule().getName();
        this.description = studentModule.getModule().getDescription();
    }
}
