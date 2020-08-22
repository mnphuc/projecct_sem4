package com.project.sem4.model;

import java.util.Date;

public class Order {
//    OrderId int primary key identity(1,1),
//    UserId int,
//    Total int not null,
//    PaymentId int ,
//    Phone varchar(255) not null,
//    Address nvarchar(255) not null,
//    Description nvarchar(255),
//    CreateDate Datetime default getdate(),
//    Status int,
    int Id;
    int UserId;
    int Total;
    int PaymentId;
    String Phone;
    String Address;
    String Description;
    Date CreateDate;
    int Status;

    public Order() {
    }

    public Order(int id, int userId, int total, int paymentId, String phone, String address, String description, Date createDate, int status) {
        Id = id;
        UserId = userId;
        Total = total;
        PaymentId = paymentId;
        Phone = phone;
        Address = address;
        Description = description;
        CreateDate = createDate;
        Status = status;
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

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public int getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(int paymentId) {
        PaymentId = paymentId;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
