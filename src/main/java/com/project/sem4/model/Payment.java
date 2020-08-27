package com.project.sem4.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Data
public class Payment {
//    Id int primary key identity(1,1),
//    Name nvarchar(255),
//    Description nvarchar(255),
//    Title nvarchar(255),
//    CreateAt datetime default getdate()
    Integer id;
    @NotEmpty(message = "Tên Phương Thức Không Được Để Trống")
    String name;
    @NotEmpty(message = "Mô tả phương thức thanh toán không được để trống")
    String description;
    String title;
    Date createAt;
}
