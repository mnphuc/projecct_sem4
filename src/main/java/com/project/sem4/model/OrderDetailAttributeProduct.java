package com.project.sem4.model;

public class OrderDetailAttributeProduct {
//    OrderDetailId int,
//    AttributeProductId int,
    int OrderDetailId;
    int AttributeProductId;

    public OrderDetailAttributeProduct() {
    }

    public OrderDetailAttributeProduct(int orderDetailId, int attributeProductId) {
        OrderDetailId = orderDetailId;
        AttributeProductId = attributeProductId;
    }

    public int getOrderDetailId() {
        return OrderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        OrderDetailId = orderDetailId;
    }

    public int getAttributeProductId() {
        return AttributeProductId;
    }

    public void setAttributeProductId(int attributeProductId) {
        AttributeProductId = attributeProductId;
    }
}
