package com.project.sem4.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
@Data
public class Products {
//    Id Bigint primary key identity(1,1),
//    ProductName nvarchar(255) not null,
//    CategoryId int,
//    ProductPrice float not null,
//    Image_Link varchar(255),
//    Image_List text,
//    Quantity int default(1),
//    ProductPriceSale float,
//    Note int default(1),
//    Sale_Status int default(1),
//    Description text,
//	[View] int,
//    Meta_KeyWord nvarchar(255),
//    Meta_Title nvarchar(255),
//    Meta_Description nvarchar(255),
//    seo_url varchar(255),
//    Create_at datetime default getdate(),
//    Status bit
    private Long id;
    private String productName;
    private Double price;
    private String imageLink;
    private String imageList;
    private Integer quantity;
    private Double priceSale;
    private Integer note;
    private Integer saleStatus;
    private String description;
    private Integer view;
    private String metaTitle;
    private String metaKeyWord;
    private String metaDescription;
    private String slug;
    private Date createAt;
    private Boolean status;
}
