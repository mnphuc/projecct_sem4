package com.project.sem4.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class Order {
    Integer id;
    Long userId;
    @NotEmpty(message = "Tên người nhận không được để trống")
    String fullName;
    Integer total;
    Double totalPrice;
    Integer paymentId;
    @NotEmpty(message = "Số điện thoại không được để trống")
    String phone;
    @NotEmpty(message = "Địa chỉ không được để trống")
    String address;
    String description;
    Date createDate;
    Integer status;
}
