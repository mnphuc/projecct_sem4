package com.project.sem4.repository;

import com.project.sem4.model.Popup;
import com.project.sem4.repository.interfaces.SettingRepository;
import com.project.sem4.vendor.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Repository
public class SettingRepositoryImpl implements SettingRepository {

    @Override
    public List<Popup> getAllPopup() {
        List<Popup> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllPopup}");
            rs = cs.executeQuery();
            while (rs.next()){
                Popup popup = new Popup();
                popup.setId(rs.getInt("id"));
                popup.setTitle(rs.getString("title"));
                popup.setContent(rs.getString("content"));
                popup.setImageBackground(rs.getString("imageBackground"));
                popup.setStatus(rs.getBoolean("status"));
                popup.setDateStart(rs.getDate("dateStart"));
                popup.setDateEnd(rs.getDate("dateEnd"));
                popup.setCreateAt(rs.getDate("create_At"));
                list.add(popup);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public Boolean insertPopup(Popup popup) {
        Boolean bl =false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call insertPopup (?,?,?,?,?,?)}");
            cs.setString(1, popup.getTitle());
            cs.setString(2, popup.getContent());
            cs.setString(3, popup.getImageBackground());
            cs.setInt(4,0);
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            String dateS = formatter.format(popup.getDateStart());
            String dateE = formatter.format(popup.getDateEnd());
            cs.setString(5,dateS);
            cs.setString(6, dateE);
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
    public Popup findPopupById(Integer id) {
        Popup popup = new Popup();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findPopupById (?)}");
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()){
                popup.setId(rs.getInt("id"));
                popup.setTitle(rs.getString("title"));
                popup.setContent(rs.getString("content"));
                popup.setImageBackground(rs.getString("imageBackground"));
                popup.setStatus(rs.getBoolean("status"));
                popup.setDateStart(rs.getDate("dateStart"));
                popup.setDateEnd(rs.getDate("dateEnd"));
                popup.setCreateAt(rs.getDate("createAt"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return popup;
    }

    @Override
    public Boolean updatePopup(Popup popup) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call updatePopup}");
            cs.setInt(1, popup.getId());
            cs.setString(2, popup.getTitle());
            cs.setString(3, popup.getContent());
            cs.setString(4, popup.getImageBackground());
            cs.setInt(5, 0);
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            String dateS = formatter.format(popup.getDateStart());
            String dateE = formatter.format(popup.getDateEnd());
            cs.setString(6, dateS);
            cs.setString(7, dateE);
            int i = cs.executeUpdate();
            if (i >0){
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
    public Boolean deletePopup(Integer id) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call deletePopup (?)}");
            cs.setInt(1, id);
            int i = cs.executeUpdate();
            if (i >0){
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
