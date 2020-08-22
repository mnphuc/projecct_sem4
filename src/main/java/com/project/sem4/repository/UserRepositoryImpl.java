package com.project.sem4.repository;

import com.project.sem4.model.Users;
import com.project.sem4.repository.interfaces.UserRepository;
import com.project.sem4.vendor.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public Users loadUserByUsername(String email) {
        Users users = new Users();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findUserByEmail (?)}");
            cs.setString(1, email);
            rs = cs.executeQuery();
            while (rs.next()){
                users.setUserID(rs.getLong("userID"));
                users.setFullName(rs.getString("FullName"));
                users.setBirthday(rs.getDate("birthday"));
                users.setPhoneNumber(rs.getString("PhoneNumber"));
                users.setEmail(rs.getString("email"));
                users.setAddress(rs.getString("address"));
                users.setPassword(rs.getString("password"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return users;
    }

    @Override
    public List<String> getRoleByUser(Long userId) {
        List<String> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getRoleByUser (?)}");
            cs.setLong(1, userId);
            rs = cs.executeQuery();
            while (rs.next()){
                String abc = rs.getString("roleName");
                list.add(abc);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;

    }


}
