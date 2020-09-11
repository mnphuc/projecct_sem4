package com.project.sem4.repository;

import com.project.sem4.model.Categories;
import com.project.sem4.repository.interfaces.CategoriesRepository;
import com.project.sem4.vendor.DBConnect;
import com.project.sem4.vendor.MnpSlug;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CategoriesRepositoryImpl implements CategoriesRepository {

    @Override
    public List<Categories> getAllCategories() {
        List<Categories> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllCategories}");
            rs = cs.executeQuery();
            while (rs.next()){
                Categories categories = new Categories();
                categories.setId(rs.getInt("id"));
                categories.setCategoryName(rs.getString("categoryName"));
                categories.setImage(rs.getString("image"));
                categories.setDescription(rs.getString("description"));
                categories.setUrl(rs.getString("url"));
                categories.setParentId(rs.getInt("parent_id"));
                categories.setCreateDate(rs.getDate("createDate"));
                categories.setStatus(rs.getBoolean("status"));
                list.add(categories);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public Boolean insertCategories(Categories categories) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call insertCategories (?,?,?,?,?,?)}");
            cs.setString(1, categories.getCategoryName());
            String slug = MnpSlug.makeSlug(categories.getCategoryName());
            cs.setString(2, categories.getImage());
            cs.setString(3, categories.getDescription());
            cs.setString(4, slug);
            cs.setInt(5, categories.getParentId());
            cs.setBoolean(6, categories.getStatus());
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
    public Boolean updateCategories(Categories categories) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();

        try {
            cs = conn.prepareCall("{call updateCategories (?,?,?,?,?,?,?)}");
            cs.setInt(1, categories.getId());
            cs.setString(2, categories.getCategoryName());
            String slug = MnpSlug.makeSlug(categories.getCategoryName());
            cs.setString(3, categories.getImage());
            cs.setString(4, categories.getDescription());
            cs.setString(5, slug);
            cs.setInt(6, categories.getParentId());
            cs.setBoolean(7, categories.getStatus());
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
    public Categories findCategoryById(Integer id) {
        Categories categories = new Categories();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();

        try {
            cs = conn.prepareCall("{call findCategoryById (?)}");
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()){
                categories.setId(rs.getInt("id"));
                categories.setCategoryName(rs.getString("categoryName"));
                categories.setImage(rs.getString("image"));
                categories.setDescription(rs.getString("description"));
                categories.setUrl(rs.getString("url"));
                categories.setParentId(rs.getInt("parent_id"));
                categories.setCreateDate(rs.getDate("createDate"));
                categories.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return categories;
    }

    @Override
    public Boolean findCategoyByName(String cateName) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();

        try {
            cs = conn.prepareCall("{call findCategoyByName (?)}");
            cs.setString(1, cateName);
            rs = cs.executeQuery();
            int i = 0;
            while (rs.next()){
                i ++;
            }
            if (i ==0){
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
    public Boolean deleteCategories(Integer id) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call deleteCategories (?)}");
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
