package com.project.sem4.model;

import java.math.BigInteger;

public class OrderDetail {
//    Id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
//    OrderId	INT,
//    ProductId bigInt,
//    ProductAttribute varchar(100),
//    Total INT default(1) not null,
//    Price FLOAT NOT NULL
    int Id;
    int OrderId;
    Long ProductId;
    int Total;
    Double Price;

    public OrderDetail() {
    }

    public OrderDetail(int id, int orderId, Long productId, int total, Double price) {
        Id = id;
        OrderId = orderId;
        ProductId = productId;
        Total = total;
        Price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }
}
