package com.project.sem4.model;

import java.math.BigInteger;
import java.util.Date;

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

    public Rating() {
    }

    public Rating(int id, int starRating, String comment, int userId, Long productId, Date createAt) {
        Id = id;
        StarRating = starRating;
        Comment = comment;
        UserId = userId;
        ProductId = productId;
        CreateAt = createAt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getStarRating() {
        return StarRating;
    }

    public void setStarRating(int starRating) {
        StarRating = starRating;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }

    public Date getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(Date createAt) {
        CreateAt = createAt;
    }
}
