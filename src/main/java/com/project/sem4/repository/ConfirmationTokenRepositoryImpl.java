package com.project.sem4.repository;

import com.project.sem4.model.service.ConfirmationToken;
import com.project.sem4.repository.interfaces.ConfirmationTokenRepository;
import com.project.sem4.vendor.DBConnect;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Repository
public class ConfirmationTokenRepositoryImpl implements ConfirmationTokenRepository {

    @Override
    public Boolean addConfigToken(ConfirmationToken addConfigToken) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call addConfigToken (?,?,?)}");
            cs.setString(1,   addConfigToken.getConfirmationToken());
            cs.setString(2, addConfigToken.getCreatedDate().toString());
            cs.setLong(3, addConfigToken.getUserId());
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
    public Boolean deleteToken(Long id) {
        Boolean bl =false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call deleteToken(?)}");
            cs.setLong(1, id);
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
    public ConfirmationToken findTokenByToken(String token) {
        ConfirmationToken confirmationToken = new ConfirmationToken();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findTokenByToken(?)}");
            cs.setString(1, token);
            rs = cs.executeQuery();
            while (rs.next()){
                confirmationToken.setId(rs.getLong("id"));
                confirmationToken.setConfirmationToken(rs.getString("confirmation_token"));
                LocalDate myObj = LocalDate.now();
                confirmationToken.setCreatedDate(myObj);
                confirmationToken.setUserId(rs.getLong("userId"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return confirmationToken;
    }
}
