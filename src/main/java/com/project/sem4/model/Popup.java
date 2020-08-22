package com.project.sem4.model;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class Popup {
//    Id int primary key identity(1,1),
//    Title nvarchar(255) not null,
//    Content_Popup nvarchar(255) not null,
//    Image_Popup varchar(255),
//	[Status] int default(0),
//    DateStart datetime,
//    create_at datetime default getdate(),
    private Integer id;
    @NotEmpty(message = "Tiêu Đề Không Được Đê Trống")
    private String title;
    @NotEmpty(message = "Nội Dung Không Được Để Trống")
    private String content;
    private String imageBackground;
   private Boolean status;
    private Date dateStart;
    private Date dateEnd;
    private Date createAt;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(String imageBackground) {
        this.imageBackground = imageBackground;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
