package com.project.sem4.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
@Data
public class Rating {
//    Id int primary key identity(1,1),
//    StarRating int default(5),
//    Comment nvarchar(255),
//    UserId int,
//    ProductId bigint,
//    create_at datetime default getdate()
    int Id;
    int StarRating;
    String Comment;
    int UserId;
    Long ProductId;
    Date CreateAt;
}
