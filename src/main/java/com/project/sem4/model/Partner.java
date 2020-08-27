package com.project.sem4.model;

import lombok.Data;

import java.util.Date;
@Data
public class Partner {
//    Id int primary key identity(1,1),
//    ImageLogo varchar(255),
//    Title nvarchar(255),
//    Url_Redirect varchar(255),
//    Description nvarchar(255),
//    Create_At datetime default getdate()
    Integer Id;
    String ImageLogo;
    String Title;
    String UrlRedirect;
    String Description;
    Date CreateAt;

}
