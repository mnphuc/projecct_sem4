package com.project.sem4.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class Blogs {
//    Id int primary key identity(1,1),
//    CategoryBlogId int,
//    Content text,
//    Image varchar(255),
//    Tag nvarchar(255),
//    title nvarchar(255),
//    Meta_Title nvarchar(255),
//    Meta_Content nvarchar(255),
//    Meta_Keyword nvarchar(255),
//    UserId int,
//    Create_At datetime default getdate()
    Integer id;
    @NotNull(message = "Phải Chọn Danh Mục")
    Integer blogCategoryId;
    @NotEmpty(message = "Phải Có Nội Dung")
    String content;
    String image;
    String slug;
    String tag;
    @NotEmpty(message = "Phải Có Tiêu Đề")
    String title;
    String metaTitle;
    String metaContent;
    String metaKeyword;
    Long userId;
    Date createAt;

}
