package com.project.sem4.repository;

import com.project.sem4.model.Banner;
import com.project.sem4.model.Payment;
import com.project.sem4.repository.interfaces.BannerRepository;
import com.project.sem4.vendor.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BannerRepositoryImpl implements BannerRepository {
    @Override
    public List<Banner> getAllBanner() {
        List<Banner> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllBanner}");
            rs = cs.executeQuery();
            while (rs.next()){
                Banner banner = new Banner();
                banner.setId(rs.getInt("id"));
                banner.setImageLink(rs.getString("imageLink"));
                banner.setTitleBanner(rs.getString("titleBanner"));
                banner.setDescription(rs.getString("description"));
                banner.setLinkRedirect(rs.getString("linkRedirect"));
                banner.setCreateDate(rs.getDate("createDate"));
                banner.setTypeBanner(rs.getInt("typeBanner"));
                banner.setStatus(rs.getBoolean("status"));
                list.add(banner);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public Boolean insertBanner(Banner banner) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call createBanner(?,?,?,?,?,?)}");
            cs.setString(1, banner.getImageLink());
            cs.setString(2, banner.getTitleBanner());
            cs.setString(3, banner.getDescription());
            cs.setString(4, banner.getLinkRedirect());
            cs.setInt(5, banner.getTypeBanner());
            cs.setBoolean(6, banner.getStatus());
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
    public Banner findBannerById(Integer id) {
        Banner banner = new Banner();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findBannerById (?)}");
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()){
                banner.setId(rs.getInt("id"));
                banner.setImageLink(rs.getString("imageLink"));
                banner.setTitleBanner(rs.getString("titleBanner"));
                banner.setDescription(rs.getString("description"));
                banner.setLinkRedirect(rs.getString("linkRedirect"));
                banner.setCreateDate(rs.getDate("createDate"));
                banner.setTypeBanner(rs.getInt("typeBanner"));
                banner.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return banner;
    }

    @Override
    public Boolean updateBanner(Banner banner) {
        Boolean bl =false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call updateBanner (?,?,?,?,?,?,?)}");
            cs.setInt(1, banner.getId());
            cs.setString(2, banner.getImageLink());
            cs.setString(3, banner.getTitleBanner());
            cs.setString(4, banner.getDescription());
            cs.setString(5, banner.getLinkRedirect());
            cs.setInt(6, banner.getTypeBanner());
            cs.setBoolean(7, banner.getStatus());
            int i = cs.executeUpdate();
            if (i>0){
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
    public Boolean deleteBanner(Integer id) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call deleteBanner (?)}");
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
