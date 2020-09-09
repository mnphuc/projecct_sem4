package com.project.sem4.repository;

import com.project.sem4.model.Products;
import com.project.sem4.model.view.OrderView;
import com.project.sem4.repository.interfaces.OrderRepository;
import com.project.sem4.vendor.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public List<OrderView> getAllOder() {
        List<OrderView> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllOder}");
            rs = cs.executeQuery();
            while (rs.next()){
                OrderView orderView = new OrderView();
                orderView.setId(rs.getInt("id"));
                orderView.setUserName(rs.getString("fullname"));
                orderView.setTotal(rs.getInt("total"));
                orderView.setPaymentName(rs.getString("name"));
                orderView.setPhone(rs.getString("phone"));
                orderView.setAddress(rs.getString("address"));
                orderView.setDescription(rs.getString("description"));
                Timestamp timestamp = rs.getTimestamp("createDate");
                Date date = new Date(timestamp.getTime());
                orderView.setAddress(String.valueOf(date));
                list.add(orderView);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }
}
