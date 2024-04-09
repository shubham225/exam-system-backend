package com.exam.system.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Module extends BaseModel{
    private String name;
    private String description;
    @ManyToMany(mappedBy = "modules")
    private Set<Exam> exam;
}
