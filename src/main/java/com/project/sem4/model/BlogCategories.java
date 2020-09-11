package com.project.sem4.model;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class BlogCategories {
//    Id int primary key identity(1,1),
//    Name nvarchar(255),
//    Description nvarchar(255),
//    Create_At datetime default getdate()
    Integer id;
    @NotEmpty(message = "tên danh mục không được để trống")
    String name;
    String description;
    String slug;
    Date createAt;


}
