package com.project.sem4.repository;

import com.project.sem4.model.*;
import com.project.sem4.model.map.OrderDetailMap;
import com.project.sem4.model.view.OrderView;
import com.project.sem4.repository.interfaces.OrderRepository;
import com.project.sem4.vendor.DBConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Autowired
    ProductRepositoryImpl productRepository;
    @Override
    public List<OrderDetailMap> getAllOrderDetail(Integer idOrder) {
        List<OrderDetailMap> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getOrderDetailByOrderId (?)}");
            cs.setInt(1, idOrder);
            rs = cs.executeQuery();
            while (rs.next()){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(rs.getInt("id"));
                orderDetail.setOrderId(rs.getInt("orderId"));
                orderDetail.setProductId(rs.getLong("productId"));
                orderDetail.setTotal(rs.getInt("total"));
                orderDetail.setPrice(rs.getDouble("price"));
                OrderDetailMap orderDetailMap = new OrderDetailMap();
                orderDetailMap.setOrderDetail(orderDetail);
                orderDetailMap.setProducts(productRepository.getProductById(rs.getLong("productId")));
                orderDetailMap.setAttributes(getAttributeByOrderDetailId(orderDetail.getId()));
                list.add(orderDetailMap);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public List<OrderView> getOrdrByuserId(Long userId) {
        List<OrderView> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getOrdrByuserId (?)}");
            cs.setLong(1, userId);
            rs = cs.executeQuery();
            while (rs.next()){
                OrderView orderView = new OrderView();
                orderView.setId(rs.getInt("id"));
                orderView.setUserName(rs.getString("fullname"));
                orderView.setTotal(rs.getInt("total"));
                orderView.setPaymentName(rs.getString("name"));
                orderView.setPhone(rs.getString("phone"));
                orderView.setTotalPrice(rs.getDouble("totalPrice"));
                orderView.setAddress(rs.getString("address"));
                orderView.setDescription(rs.getString("description"));
                Timestamp timestamp = rs.getTimestamp("createDate");
                Date date = new Date(timestamp.getTime());
                orderView.setCreateDate(date);
                orderView.setStatus(rs.getInt("status"));
                list.add(orderView);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public OrderView getOrderById(Integer id) {
        OrderView orderView = new OrderView();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllOder}");
            rs = cs.executeQuery();
            while (rs.next()){
                orderView.setId(rs.getInt("id"));
                orderView.setUserName(rs.getString("fullname"));
                orderView.setTotal(rs.getInt("total"));
                orderView.setPaymentName(rs.getString("name"));
                orderView.setPhone(rs.getString("phone"));
                orderView.setAddress(rs.getString("address"));
                orderView.setDescription(rs.getString("description"));
                Timestamp timestamp = rs.getTimestamp("createDate");
                Date date = new Date(timestamp.getTime());
                orderView.setCreateDate(date);
                orderView.setStatus(rs.getInt("status"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return orderView;
    }

    @Override
    public List<Attribute> getAttributeByOrderDetailId(Integer orderDetailId) {
        List<Attribute> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAttributeByOrderDetailId (?)}");
            cs.setInt(1, orderDetailId);
            rs = cs.executeQuery();
            while (rs.next()){
                Attribute attribute= new Attribute();
                attribute.setId(rs.getInt("id"));
                attribute.setAttributeSetId(rs.getInt("AttributeSetId"));
                attribute.setDescription(rs.getString("description"));
                attribute.setName(rs.getString("name"));
                attribute.setDataType(rs.getString("dataType"));
                attribute.setDefaultValue(rs.getString("defaultValue"));
                list.add(attribute);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

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
                orderView.setCreateDate(date);
                orderView.setStatus(rs.getInt("status"));
                list.add(orderView);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public Integer insertOrderCheckOut(Order order) {
        Integer idOrder = 0;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call insertOrderCheckOut (?,?,?,?,?,?,?,?)}");
            cs.setLong(1, order.getUserId());
            cs.setString(2, order.getFullName());
            cs.setInt(3, order.getTotal());
            cs.setDouble(4, order.getTotalPrice());
            cs.setInt(5, order.getPaymentId());
            cs.setString(6, order.getPhone());
            cs.setString(7, order.getAddress());
            cs.setString(8, order.getDescription());
            rs = cs.executeQuery();
                while (rs.next()){
                    System.out.println(rs.getInt("idOrder"));
                    idOrder = rs.getInt("idOrder");
                }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs, rs);
        }
        return idOrder;
    }

    @Override
    public Integer insertOrderDetail(OrderDetail orderDetail) {
        Integer idOrderDetail = 0;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call insertOrderDetail(?,?,?,?) }");
            cs.setInt(1, orderDetail.getOrderId());
            cs.setLong(2, orderDetail.getProductId());
            cs.setInt(3, orderDetail.getTotal());
            cs.setDouble(4, orderDetail.getPrice());
            rs = cs.executeQuery();
            while (rs.next()){
                idOrderDetail = rs.getInt("idOrder");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return idOrderDetail;
    }

    @Override
    public Boolean insertOrderDetailAttribute(OrderDetailAttributeProduct orderDetailAttributeProduct) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call insertOrderDetailAttribute (?,?)}");
            cs.setInt(1, orderDetailAttributeProduct.getOrderDetailId());
            cs.setInt(2, orderDetailAttributeProduct.getAttributeProductId());
            int i = cs.executeUpdate();
            if (i >0 ){
                bl = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return bl;
    }

    @Override
    public Boolean updateStatusOrder(Integer orderId, Integer status) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call updateStatusOrder(?,?)}");
            cs.setInt(1, orderId);
            cs.setInt(2, status);
            int i = cs.executeUpdate();
            if (i > 0){
                bl = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return bl;
    }
}
