package com.project.sem4.model.view;

import com.project.sem4.model.Attribute;
import com.project.sem4.model.Products;
import lombok.Data;

import java.util.List;
@Data
public class CartInfo {
    private Products products;
    private Integer quantity;
    private List<Attribute> attribute;
}
