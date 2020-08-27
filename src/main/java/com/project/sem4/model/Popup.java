package com.project.sem4.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Data
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

}
