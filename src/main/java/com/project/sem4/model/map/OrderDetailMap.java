package com.project.sem4.model.map;

import com.project.sem4.model.Attribute;
import com.project.sem4.model.Order;
import com.project.sem4.model.OrderDetail;
import com.project.sem4.model.Products;
import com.project.sem4.model.view.OrderView;
import lombok.Data;

import java.util.List;
@Data
public class OrderDetailMap {
    private OrderDetail orderDetail;
    private Products products;
    private List<Attribute> attributes;
}
