package com.project.sem4.model.view;

import com.project.sem4.model.Attribute;
import com.project.sem4.model.Products;

public class CartInfo {
    private Integer key;
    private Products products;
    private Integer quantity;
    private String[] attribute;

    public CartInfo() {
    }



    public CartInfo(Integer key, Products products, Integer quantity, String[] attribute) {
        this.key = key;
        this.products = products;
        this.quantity = quantity;
        this.attribute = attribute;
    }
    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String[] getAttribute() {
        return attribute;
    }

    public void setAttribute(String[] attribute) {
        this.attribute = attribute;
    }
}
