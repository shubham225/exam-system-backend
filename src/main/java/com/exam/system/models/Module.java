package com.exam.system.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Module extends BaseModel{
    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "module_exam",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private Set<Exam> exam;

    @OneToMany(
            mappedBy = "module",
            cascade = CascadeType.ALL
    )
    private Set<Question> questions;
}
