package com.project.sem4.model;

import java.util.Date;

public class Partner {
//    Id int primary key identity(1,1),
//    ImageLogo varchar(255),
//    Title nvarchar(255),
//    Url_Redirect varchar(255),
//    Description nvarchar(255),
//    Create_At datetime default getdate()
    int Id;
    String ImageLogo;
    String Title;
    String UrlRedirect;
    String Description;
    Date CreateAt;

    public Partner() {
    }

    public Partner(int id, String imageLogo, String title, String urlRedirect, String description, Date createAt) {
        Id = id;
        ImageLogo = imageLogo;
        Title = title;
        UrlRedirect = urlRedirect;
        Description = description;
        CreateAt = createAt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getImageLogo() {
        return ImageLogo;
    }

    public void setImageLogo(String imageLogo) {
        ImageLogo = imageLogo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUrlRedirect() {
        return UrlRedirect;
    }

    public void setUrlRedirect(String urlRedirect) {
        UrlRedirect = urlRedirect;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(Date createAt) {
        CreateAt = createAt;
    }
}
