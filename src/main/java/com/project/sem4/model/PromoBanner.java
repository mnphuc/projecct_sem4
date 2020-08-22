package com.project.sem4.model;

import java.util.Date;

public class PromoBanner {
//    Id int primary key identity(1,1),
//    UrlRedirect varchar(255),
//    LinkImage varchar(255),
//    Description nvarchar(255),
//    TypeSlider int,
//    CreateDate Datetime default getdate()
    int Id;
    String UrlRedirect;
    String LinkImage;
    String Description;
    int TypeSlider;
    Date CreateDate;

    public PromoBanner() {
    }

    public PromoBanner(int id, String urlRedirect, String linkImage, String description, int typeSlider, Date createDate) {
        Id = id;
        UrlRedirect = urlRedirect;
        LinkImage = linkImage;
        Description = description;
        TypeSlider = typeSlider;
        CreateDate = createDate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUrlRedirect() {
        return UrlRedirect;
    }

    public void setUrlRedirect(String urlRedirect) {
        UrlRedirect = urlRedirect;
    }

    public String getLinkImage() {
        return LinkImage;
    }

    public void setLinkImage(String linkImage) {
        LinkImage = linkImage;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getTypeSlider() {
        return TypeSlider;
    }

    public void setTypeSlider(int typeSlider) {
        TypeSlider = typeSlider;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }
}
