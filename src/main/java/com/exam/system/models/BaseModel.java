package com.exam.system.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseModel {
    private int id;
    private boolean isActive;
    private Date createdOn;
}
