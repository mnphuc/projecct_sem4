package com.project.sem4.model;

import lombok.Data;

import java.math.BigInteger;
@Data
public class ProductAttribute {
//    Id int primary key identity(1,1),
//    ProductId bigint,
//    AttributeId int,
//    type varchar(100),
//    Value varchar(255)
    Integer id;
    Long productId;
    Integer attributeId;
    String type;
    String value;

}
