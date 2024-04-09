package com.exam.system.models;

import com.exam.system.enums.QuestionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question extends BaseModel {
    @Column(nullable = false, unique = true)
    private String questionText;
    @ManyToOne
    private Module module;
    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL
    )
    private Set<Option> options;
    @Enumerated
    private QuestionType questionType;
}
