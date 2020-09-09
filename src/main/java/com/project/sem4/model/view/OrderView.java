package com.project.sem4.model.view;

import lombok.Data;

import java.util.Date;
@Data
public class OrderView {
    Integer id;
    String  userName;
    Integer total;
    String paymentName;
    String phone;
    String address;
    String description;
    Date createDate;
    Integer status;

}
