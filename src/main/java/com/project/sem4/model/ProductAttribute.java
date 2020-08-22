package com.project.sem4.model;

import java.math.BigInteger;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
