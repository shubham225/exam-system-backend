package com.exam.system.dtos.exam.exam;

import com.exam.system.dtos.BaseDto;
import com.exam.system.enums.ExamStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExamByUserIdRequestDto extends BaseDto {
    private ExamStatus status;
}
