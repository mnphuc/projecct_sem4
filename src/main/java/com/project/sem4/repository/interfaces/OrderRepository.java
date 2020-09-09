package com.project.sem4.repository.interfaces;

import com.project.sem4.model.Order;
import com.project.sem4.model.view.OrderView;

import java.util.List;

public interface OrderRepository {
    public List<OrderView> getAllOder();
}
