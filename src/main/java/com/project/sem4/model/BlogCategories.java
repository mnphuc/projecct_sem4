package com.project.sem4.model;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
