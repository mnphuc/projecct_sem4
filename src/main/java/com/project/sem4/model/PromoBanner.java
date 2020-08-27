package com.project.sem4.model;

import lombok.Data;

import java.util.Date;
@Data
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
}
