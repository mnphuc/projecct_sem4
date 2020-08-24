package com.project.sem4.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Data
public class Banner {
//    BannerId INT PRIMARY KEY IDENTITY(1,1),
//    Image_link varchar(255) NOT NULL,
//    TitleBanner nvarchar(255) not null,
//    Description nvarchar(255),
//    Link_Redirect varchar(255),
//    CreateDate Datetime default getdate(),
//    Status BIT
    Integer id;
    @NotEmpty(message = "Ảnh Không Được Bỏ Trống")
    String imageLink;
    @NotEmpty(message = "Tiêu đề không đưuọc bỏ trống")
    String titleBanner;
    String description;
    String linkRedirect;
    Date createDate;
    Integer typeBanner;
    Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getTitleBanner() {
        return titleBanner;
    }

    public void setTitleBanner(String titleBanner) {
        this.titleBanner = titleBanner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkRedirect() {
        return linkRedirect;
    }

    public void setLinkRedirect(String linkRedirect) {
        this.linkRedirect = linkRedirect;
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

    public Integer getTypeBanner() {
        return typeBanner;
    }

    public void setTypeBanner(Integer typeBanner) {
        this.typeBanner = typeBanner;
    }
}
