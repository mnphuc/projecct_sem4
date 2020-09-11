package com.project.sem4.repository.interfaces;

import com.project.sem4.model.Attribute;
import com.project.sem4.model.Order;
import com.project.sem4.model.OrderDetail;
import com.project.sem4.model.OrderDetailAttributeProduct;
import com.project.sem4.model.map.OrderDetailMap;
import com.project.sem4.model.view.OrderView;

import java.util.List;

public interface OrderRepository {
    public List<OrderView> getAllOder();
    public Integer insertOrderCheckOut(Order order);
    public Integer insertOrderDetail(OrderDetail orderDetail);
    public Boolean insertOrderDetailAttribute(OrderDetailAttributeProduct orderDetailAttributeProduct);
    public List<OrderDetailMap> getAllOrderDetail(Integer idOrder);
    public List<Attribute> getAttributeByOrderDetailId(Integer orderDetailId);
    public OrderView getOrderById(Integer id);
    public Boolean updateStatusOrder(Integer orderId, Integer status);
    public List<OrderView> getOrdrByuserId(Long userId);
    public List<OrderView> getAllOrderByStatus(Integer status);
}
