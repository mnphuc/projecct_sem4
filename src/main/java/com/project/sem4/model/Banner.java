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


}
