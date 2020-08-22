package com.project.sem4.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Categories {
//    Id int primary key identity(1,1),
//    CategoryName nvarchar(255),
//    Url varchar(255),
//    parent_id int,
//    CreateDate Datetime default getdate(),
//    status bit
    private Integer id;
    @NotEmpty(message = "Tên Danh Muc không được để trống")
    private String categoryName;
    private String url;
    private Integer parentId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
