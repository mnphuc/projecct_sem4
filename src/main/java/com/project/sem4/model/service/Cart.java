package com.project.sem4.model.service;

import lombok.Data;

@Data
public class Cart {
    private Long proId;
    private Integer[] attr;
    private Integer quantity;
}
