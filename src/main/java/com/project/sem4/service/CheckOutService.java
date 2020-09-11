package com.project.sem4.service;

import com.project.sem4.model.*;
import com.project.sem4.model.map.OrderDetailMap;
import com.project.sem4.model.service.ListTask;
import com.project.sem4.model.view.CartInfo;
import com.project.sem4.model.view.InsertOrderView;
import com.project.sem4.repository.OrderRepositoryImpl;
import com.project.sem4.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CheckOutService {
    @Autowired
    UserRepositoryImpl userRepository;
    @Autowired
    OrderRepositoryImpl orderRepository;
    @Autowired
    CheckTest checkTest;
    @Autowired
    HttpSession session;
    public void checkout(InsertOrderView insertOrderView){
        HashMap<Long, CartInfo> cartItems = (HashMap<Long, CartInfo>) session.getAttribute("myCart");
        Integer total = 0;
        Double totalPrice = Double.valueOf(0);
        for (CartInfo cartInfo : cartItems.values()){
            total += cartInfo.getQuantity();
            totalPrice += cartInfo.getQuantity() * cartInfo.getProducts().getPriceSale();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.getUserByEmail(authentication.getName());
        Order order = new Order();
        order.setFullName(insertOrderView.getFullName());
        order.setUserId(user.getUserID());
        order.setTotal(total);
        order.setTotalPrice(totalPrice);
        order.setPaymentId(insertOrderView.getPaymentId());
        order.setPhone(insertOrderView.getPhone());
        String address = insertOrderView.getAddress()+ "( "+insertOrderView.getAddressCity() +", "+insertOrderView.getAddressDistrict()+", "+insertOrderView.getAddressWard() +" )";
        order.setAddress(address);
        order.setDescription(insertOrderView.getDescription());
        Integer idOrder = orderRepository.insertOrderCheckOut(order);

        for (CartInfo cartInfo : cartItems.values()){
          OrderDetail orderDetail = new OrderDetail();
          orderDetail.setOrderId(idOrder);
          orderDetail.setProductId(cartInfo.getProducts().getId());
          orderDetail.setPrice(cartInfo.getQuantity() * cartInfo.getProducts().getPriceSale());
          orderDetail.setTotal(cartInfo.getQuantity());

          Integer idOrderDetail = orderRepository.insertOrderDetail(orderDetail);
          for (Attribute attribute : cartInfo.getAttribute()){
              OrderDetailAttributeProduct orderDetailAttributeProduct = new OrderDetailAttributeProduct();
              orderDetailAttributeProduct.setOrderDetailId(idOrderDetail);
              orderDetailAttributeProduct.setAttributeProductId(attribute.getId());
              orderRepository.insertOrderDetailAttribute(orderDetailAttributeProduct);

          }
        }
        ListTask listTask = new ListTask();
        listTask.setEmail(user.getEmail());
        listTask.setCheckTask(2);
        listTask.setObject(String.valueOf(idOrder));
        checkTest.addTask(listTask);

        session.removeAttribute("myCart");
        session.removeAttribute("discountPrice");
        session.removeAttribute("codeDiscount");
    }
}
