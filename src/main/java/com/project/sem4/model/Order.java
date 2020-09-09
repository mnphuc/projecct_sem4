package com.project.sem4.model;

import lombok.Data;

import java.util.Date;
@Data
public class Order {
    Integer id;
    Integer userId;
    Integer total;
    Integer paymentId;
    String phone;
    String address;
    String description;
    Date createDate;
    Integer status;

}
