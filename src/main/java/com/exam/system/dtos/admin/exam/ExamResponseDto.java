package com.exam.system.dtos.admin.exam;

import com.exam.system.models.Exam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamResponseDto {
    private long id;
    private String examName;
    private String description;
    private int duration;

    public ExamResponseDto() {

    }

    public  ExamResponseDto(Exam exam) {
        this.id = exam.getId();
        this.examName = exam.getName();
        this.description = exam.getDescription();
    }
}
