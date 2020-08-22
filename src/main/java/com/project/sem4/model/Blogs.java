package com.project.sem4.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

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

    public Integer getBlogCategoryId() {
        return blogCategoryId;
    }

    public void setBlogCategoryId(Integer blogCategoryId) {
        this.blogCategoryId = blogCategoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaContent() {
        return metaContent;
    }

    public void setMetaContent(String metaContent) {
        this.metaContent = metaContent;
    }

    public String getMetaKeyword() {
        return metaKeyword;
    }

    public void setMetaKeyword(String metaKeyword) {
        this.metaKeyword = metaKeyword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
