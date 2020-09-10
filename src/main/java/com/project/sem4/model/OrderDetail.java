package com.project.sem4.model;

import lombok.Data;

@Data
public class OrderDetail {
    Integer id;
    Integer orderId;
    Long productId;
    Integer total;
    Double price;


}
