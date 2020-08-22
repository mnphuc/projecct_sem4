package com.project.sem4.repository;

import com.project.sem4.model.Payment;
import com.project.sem4.repository.interfaces.PaymentRepository;
import com.project.sem4.vendor.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    @Override
    public List<Payment> getAllPayment() {
        List<Payment> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllPayment}");
            rs = cs.executeQuery();
            while (rs.next()){
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setName(rs.getString("name"));
                payment.setDescription(rs.getString("description"));
                payment.setTitle(rs.getString("title"));
                payment.setCreateAt(rs.getDate("createAt"));
                list.add(payment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public Boolean insertPayment(Payment payment) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call createPayment (?,?,?)}");
            cs.setString(1, payment.getName());
            cs.setString(2, payment.getDescription());
            cs.setString(3, payment.getName());
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

    @Override
    public Payment findPaymentById(Integer id) {
        Payment payment = new Payment();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findPaymentById (?)}");
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()){
                payment.setId(rs.getInt("id"));
                payment.setName(rs.getString("name"));
                payment.setDescription(rs.getString("description"));
                payment.setTitle(rs.getString("title"));
                payment.setCreateAt(rs.getDate("createAt"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return payment;
    }

    @Override
    public Boolean updatePayment(Payment payment) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call updatePayment(?,?,?,?)}");
            cs.setInt(1, payment.getId());
            cs.setString(2, payment.getName());
            cs.setString(3, payment.getDescription());
            cs.setString(4, payment.getTitle());
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

    @Override
    public Boolean deletePayment(Integer id) {
        Boolean bl =false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call deletePayment (?)}");
            cs.setInt(1, id);
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
