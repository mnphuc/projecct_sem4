package com.project.sem4.model.view;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class OrderView {
    private Integer id;
    private String  userName;
    private Integer total;
    private Double totalPrice;
    private String paymentName;
    private String phone;
    private String address;
    private String description;
    private Date createDate;
    private Integer status;
}
