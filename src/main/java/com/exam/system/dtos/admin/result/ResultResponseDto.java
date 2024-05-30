package com.exam.system.dtos.admin.result;

import com.exam.system.dtos.BaseDto;
import com.exam.system.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponseDto extends BaseDto {
    private String userName;
    private String fullName;
    private String examName;
    private int rank;
    private String institute;
    private String score;

    public ResultResponseDto(User user, int rank, String score, String exam) {
        this.id = rank;
        this.userName = user.getUsername();
        this.fullName = user.getFullName();
        this.institute = user.getInstitute();
        this.rank = rank;
        this.score = score;
        this.examName = exam;
    }
}
