package com.project.sem4.model;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
