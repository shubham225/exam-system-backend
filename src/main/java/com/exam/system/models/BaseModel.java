package com.exam.system.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue
    private long id;
    private boolean isActive;
    private Date createdOn;

    public BaseModel() {
        isActive = true;
        createdOn = new Date();
    }
}
