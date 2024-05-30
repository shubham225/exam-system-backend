package com.exam.system.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Exam extends BaseModel{
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    private int duration;

    @ManyToMany(
            mappedBy = "exam",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private Set<Module> modules;
}
