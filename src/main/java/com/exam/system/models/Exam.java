package com.exam.system.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exam extends BaseModel{
    private String name;
    private String description;
}
