package com.project.sem4.model;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class AttributeSet implements Serializable {
//    Id int primary key identity(1,1),
//    Name varchar(255),
//    Description nvarchar(255)
    Integer id;
    @NotEmpty(message = "tên thuộc tính không được để trống")
    String name;
    String description;

}
