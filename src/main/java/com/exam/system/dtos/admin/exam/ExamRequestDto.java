package com.exam.system.dtos.admin.exam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamRequestDto {
    private String examName;
    private String description;
    private int duration;
}
