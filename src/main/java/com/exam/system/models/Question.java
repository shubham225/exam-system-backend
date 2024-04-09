package com.exam.system.models;

import com.exam.system.enums.QuestionType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Question extends BaseModel {
    private String questionText;
    @ManyToOne
    private Module module;
    @OneToMany
    private List<Option> options;
    @Enumerated
    private QuestionType questionType;
}
