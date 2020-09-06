package com.project.sem4.repository;

import com.project.sem4.model.Categories;
import com.project.sem4.model.Discount;
import com.project.sem4.repository.interfaces.DiscountRepository;
import com.project.sem4.vendor.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DiscountRepositoryImpl implements DiscountRepository {
    @Override
    public List<Discount> getAllDiscount() {
        List<Discount> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllDiscount}");
            rs = cs.executeQuery();
            while (rs.next()){
                Discount discount = new Discount();
                discount.setId(rs.getInt("id"));
                discount.setDescription(rs.getString("description"));
                discount.setCodeDiscount(rs.getString("Code_Discount"));
                discount.setMaxDiscount(rs.getDouble("maxDiscount"));
                discount.setTypeDiscount(rs.getInt("Type_Discount"));
                discount.setDiscount(rs.getString("discount"));
                discount.setDateEnd(rs.getDate("date_end"));
                discount.setDateStart(rs.getDate("date_start"));
                discount.setSelectProduct(rs.getString("select_product"));
                discount.setSelectUser(rs.getString("select_user"));
                list.add(discount);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public Boolean insertDiscount(Discount discount) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call insertDiscount (?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, discount.getDescription());
            cs.setString(2, discount.getCodeDiscount());
            cs.setInt(3, 1);
            cs.setString(4, discount.getDiscount());
            cs.setDouble(5, discount.getMaxDiscount());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");
            String dateS = formatter.format(discount.getDateStart());
            String dateE = formatter.format(discount.getDateEnd());
            cs.setString(6, dateE);
            cs.setString(7, dateS);
            cs.setString(8, "0");
            cs.setString(9, "0");
            int i = cs.executeUpdate();
            if (i > 0){
                bl =true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }

        return bl;
    }

    @Override
    public Discount findDiscountById(Integer id) {
        Discount discount = new Discount();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findDiscountById (?)}");
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()){
                discount.setId(rs.getInt("id"));
                discount.setDescription(rs.getString("description"));
                discount.setCodeDiscount(rs.getString("Code_Discount"));
                discount.setMaxDiscount(rs.getDouble("maxDiscount"));
                discount.setTypeDiscount(rs.getInt("Type_Discount"));
                discount.setDiscount(rs.getString("discount"));
                discount.setDateEnd(rs.getDate("date_end"));
                discount.setDateStart(rs.getDate("date_start"));
                discount.setSelectProduct(rs.getString("select_product"));
                discount.setSelectUser(rs.getString("select_user"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return discount;
    }

    @Override
    public Boolean editDiscount(Discount discount) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call editDiscount (?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, discount.getId());
            cs.setString(2, discount.getDescription());
            cs.setString(3, discount.getCodeDiscount());
            cs.setInt(4, discount.getTypeDiscount());
            cs.setString(5, discount.getDiscount());
            cs.setDouble(6, discount.getMaxDiscount());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");
            String dateS = formatter.format(discount.getDateStart());
            String dateE = formatter.format(discount.getDateEnd());
            cs.setString(7, dateE);
            cs.setString(8, dateS);
            cs.setString(9, "1");
            cs.setString(10, "1");
            int i = cs.executeUpdate();
            if (i > 0){
                bl =true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }

        return bl;
    }

    @Override
    public Boolean deleteDiscount(Integer id) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call deleteDiscount (?)}");
            cs.setInt(1, id);
            int i = cs.executeUpdate();
            if (i > 0){
                bl =true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }

        return bl;
    }
}
