package com.exam.system.dtos.user;

import com.exam.system.dtos.BaseDto;
import com.exam.system.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserExamAssignmentResponseDto extends BaseDto {
    private String userName;
    private boolean assigned;

    public UserExamAssignmentResponseDto(User user, boolean assigned) {
        this.id = user.getId();
        this.userName = user.getUsername();
        this.assigned = assigned;
    }
}
