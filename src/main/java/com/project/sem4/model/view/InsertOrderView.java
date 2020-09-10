package com.project.sem4.model.view;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Data
public class InsertOrderView {
    Integer id;
    Long userId;
    @NotEmpty(message = "Tên người nhận không được để trống")
    String fullName;
    Integer total;
    Integer paymentId;
    @NotEmpty(message = "Số điện thoại không được để trống")
    String phone;
    String addressCity;
    String addressDistrict;
    String addressWard;
    @NotEmpty(message = "Địa chỉ không được để trống")
    String address;
    String description;
    Date createDate;
    Integer status;
}
