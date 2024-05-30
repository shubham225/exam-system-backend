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
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_generator"
    )
    @SequenceGenerator(
            name = "sequence_generator",
            initialValue = 1000,
            allocationSize = 1
    )
    private long id;
    private boolean isActive;
    private Date createdOn;

    public BaseModel() {
        isActive = true;
        createdOn = new Date();
    }
}
