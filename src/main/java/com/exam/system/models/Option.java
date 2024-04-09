package com.exam.system.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Option extends BaseModel {
    @Column(nullable = false, unique = true)
    private String optionText;
    @ManyToOne(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    private Question question;
    private boolean isAnswer;
}
