package com.exam.system.dtos;

import com.exam.system.models.Exam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamResponseDto {
    private long id;
    private String name;
    private String description;

    public ExamResponseDto() {

    }

    public  ExamResponseDto(Exam exam) {
        this.id = exam.getId();
        this.name = exam.getName();
        this.description = exam.getDescription();
    }
}
