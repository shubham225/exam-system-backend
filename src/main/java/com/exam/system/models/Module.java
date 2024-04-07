package com.exam.system.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Module extends BaseModel{
    private String name;
    private String description;
    private Exam exam;
}
