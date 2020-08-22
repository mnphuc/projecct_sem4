package com.project.sem4.repository;

import com.project.sem4.model.BlogCategories;
import com.project.sem4.model.Blogs;
import com.project.sem4.model.view.BlogView;
import com.project.sem4.repository.interfaces.BlogRepository;
import com.project.sem4.vendor.DBConnect;
import com.project.sem4.vendor.MnpSlug;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogRepositoryImpl implements BlogRepository {
    @Override
    public List<BlogCategories> getAllBlogCategory() {
        List<BlogCategories> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllBlogCategory}");
            rs = cs.executeQuery();
            while (rs.next()){
                BlogCategories blogCategories = new BlogCategories();
                blogCategories.setId(rs.getInt("id"));
                blogCategories.setName(rs.getString("name"));
                blogCategories.setDescription(rs.getString("Description"));
                blogCategories.setSlug(rs.getString("slug"));
                blogCategories.setCreateAt(rs.getDate("createAt"));
                list.add(blogCategories);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public Boolean insertBlogCategory(BlogCategories blogCategories) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call insertBlogCategory (?,?,?)}");
            cs.setString(1, blogCategories.getName());
            cs.setString(2, blogCategories.getDescription());
            String slug = MnpSlug.makeSlug(blogCategories.getName());
            cs.setString(3, slug);
            int i = cs.executeUpdate();
            if (i > 0){
                bl= true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return bl;
    }

    @Override
    public BlogCategories findBlogCategoryById(Integer id) {
        BlogCategories blogCategories = new BlogCategories();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findBlogCategoryById(?)}");
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()){
                blogCategories.setId(rs.getInt("id"));
                blogCategories.setName(rs.getString("name"));
                blogCategories.setDescription(rs.getString("Description"));
                blogCategories.setCreateAt(rs.getDate("createAt"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return blogCategories;
    }

    @Override
    public Boolean editBlogCategory(BlogCategories blogCategories) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call editBlogCategory (?,?,?)}");
            cs.setInt(1, blogCategories.getId());
            cs.setString(2, blogCategories.getName());
            cs.setString(3, blogCategories.getDescription());
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
    public Boolean deleteBlogCategory(Integer id) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call deleteBlogCategory (?)}");
            cs.setInt(1, id);
            int i = cs.executeUpdate();
            if (i> 0){
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
    public List<BlogView> getAllBlog()  {
        List<BlogView> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllBlog}");
            rs = cs.executeQuery();
            while (rs.next()){
                BlogView blogView = new BlogView();
                blogView.setId(rs.getInt("id"));
                blogView.setBlogCategoryId(rs.getInt("BlogCategoryId"));
                blogView.setContent(rs.getString("Content"));
                blogView.setImage(rs.getString("image"));
                blogView.setSlug(rs.getString("slug"));
                blogView.setTag(rs.getString("tag"));
                blogView.setTitle(rs.getString("title"));
                blogView.setMetaTitle(rs.getString("MetaTitle"));
                blogView.setMetaContent(rs.getString("MetaContent"));
                blogView.setUserId(rs.getInt("UserId"));
                blogView.setCreateAt(rs.getDate("create_At"));
                list.add(blogView);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public Boolean insertBlog(Blogs blogs) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call createBlog(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, blogs.getBlogCategoryId());
            cs.setString(2, blogs.getContent());
            cs.setString(3, blogs.getImage());
            String slug = MnpSlug.makeSlug(blogs.getTitle());
            cs.setString(4, slug);
            cs.setString(5, blogs.getTag());
            cs.setString(6, blogs.getTitle());
            cs.setString(7, blogs.getTitle());
            cs.setString(8, blogs.getTitle());
            cs.setString(9, blogs.getTitle());
            cs.setLong(10, blogs.getUserId());
            int i = cs.executeUpdate();
            if (i > 0){
                bl = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bl;
    }

    @Override
    public Blogs findBlogById(Integer id) {
        Blogs blogs= new Blogs();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findBlogById (?)}");
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()){
                blogs.setId(rs.getInt("id"));
                blogs.setBlogCategoryId(rs.getInt("BlogCategoryId"));
                blogs.setContent(rs.getString("Content"));
                blogs.setImage(rs.getString("image"));
                blogs.setSlug(rs.getString("slug"));
                blogs.setTag(rs.getString("tag"));
                blogs.setTitle(rs.getString("title"));
                blogs.setMetaTitle(rs.getString("MetaTitle"));
                blogs.setMetaContent(rs.getString("MetaContent"));
                blogs.setUserId(rs.getLong("UserId"));
                blogs.setCreateAt(rs.getDate("create_At"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return blogs;
    }

    @Override
    public Boolean editBlog(Blogs blogs) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call editBlog (?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, blogs.getId());
            cs.setInt(2, blogs.getBlogCategoryId());
            cs.setString(3, blogs.getContent());
            cs.setString(4, blogs.getImage());
            String slug = MnpSlug.makeSlug(blogs.getTitle());
            cs.setString(5, slug);
            cs.setString(6, blogs.getTitle());
            cs.setString(7, blogs.getTitle());
            cs.setString(8, blogs.getTitle());
            cs.setString(9, blogs.getTitle());
            cs.setString(10, blogs.getTitle());
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
    public Boolean deleteBlog(Integer id) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call deleteBlog (?)}");
            cs.setInt(1, id);
            int i = cs.executeUpdate();
            if (i>0){
                bl=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return bl;
    }
}
