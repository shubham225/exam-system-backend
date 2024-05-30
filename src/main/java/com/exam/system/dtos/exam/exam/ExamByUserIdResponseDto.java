package com.exam.system.dtos.exam.exam;

import com.exam.system.dtos.BaseDto;
import com.exam.system.enums.ExamStatus;
import com.exam.system.models.StudentExam;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExamByUserIdResponseDto extends BaseDto {
    private String name;
    private String description;
    private ExamStatus status;
    private int duration;
    private Date startTime;
    private Date endTime;

    public ExamByUserIdResponseDto() { }

    public ExamByUserIdResponseDto(StudentExam studentExam) {
        this.id = studentExam.getId();
        this.name = studentExam.getExam().getName();
        this.description = studentExam.getExam().getDescription();
        this.status = studentExam.getStatus();
        this.duration = studentExam.getExam().getDuration();
        this.startTime = studentExam.getStartTime();
        this.endTime = studentExam.getEndTime();
    }
}
