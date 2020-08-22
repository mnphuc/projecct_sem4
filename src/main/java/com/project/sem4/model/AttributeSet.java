package com.project.sem4.model;

import javax.validation.constraints.NotEmpty;

public class AttributeSet {
//    Id int primary key identity(1,1),
//    Name varchar(255),
//    Description nvarchar(255)
    Integer id;
    @NotEmpty(message = "tên thuộc tính không được để trống")
    String name;
    String description;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
