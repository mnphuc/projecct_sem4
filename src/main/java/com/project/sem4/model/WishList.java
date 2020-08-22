package com.project.sem4.model;

import java.math.BigInteger;
import java.util.Date;

public class WishList {
    int Id;
    int UserId;
    Long ProductId;
    Date Create_At;

    public WishList() {
    }

    public WishList(int id, int userId, Long productId, Date create_At) {
        Id = id;
        UserId = userId;
        ProductId = productId;
        Create_At = create_At;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public Date getCreate_At() {
        return Create_At;
    }

    public void setCreate_At(Date create_At) {
        Create_At = create_At;
    }
}
