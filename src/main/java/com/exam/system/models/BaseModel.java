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
            strategy = GenerationType.SEQUENCE
    )
//    @SequenceGenerator(
//            name = "inc_by_one_from_thousand",
//            initialValue = 1000,
//            allocationSize = 1
//    )
    private long id;
    private boolean isActive;
    private Date createdOn;
}
