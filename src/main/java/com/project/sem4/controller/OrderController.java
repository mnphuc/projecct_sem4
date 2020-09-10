package com.project.sem4.controller;

import com.project.sem4.model.OrderDetail;
import com.project.sem4.model.map.OrderDetailMap;
import com.project.sem4.model.view.OrderView;
import com.project.sem4.repository.OrderRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("quan-tri/don-hang")
public class OrderController {
    @Autowired
    OrderRepositoryImpl orderRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String orderView(Model model){
        List<OrderView> list = orderRepository.getAllOder();

        model.addAttribute("listOrder", list);
        return "admin/order/listOrder";
    }
    @RequestMapping(value = "chi-tiet", method = RequestMethod.GET)
    public String orderDetailView(@RequestParam(value = "id", required = false)Integer id, Model model){
        List<OrderDetailMap> listOrderDetail = orderRepository.getAllOrderDetail(id);
        OrderView  orderView = orderRepository.getOrderById(id);
        model.addAttribute("order", orderView);
        model.addAttribute("orderDetail", listOrderDetail);
        return "admin/order/orderDetail";
    }
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String updateStatusOrder(@RequestParam(value = "id", required = false)Integer id, @RequestParam(value = "status", required = false)Integer status){
        orderRepository.updateStatusOrder(id,status);
        return "redirect:/quan-tri/don-hang";
    }
}
