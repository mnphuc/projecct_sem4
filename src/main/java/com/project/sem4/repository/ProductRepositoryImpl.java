package com.project.sem4.repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.sem4.model.Products;
import com.project.sem4.model.view.InsertProductModel;
import com.project.sem4.repository.interfaces.ProductRepository;
import com.project.sem4.vendor.DBConnect;
import com.project.sem4.vendor.MnpSlug;
import org.springframework.stereotype.Repository;

import javax.sound.midi.MidiDevice;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public List<Products> getAllProducts() {
        List<Products> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllProducts}");
            rs = cs.executeQuery();
            while (rs.next()){
                Products products = new Products();
                products.setId(rs.getLong("id"));
                products.setProductName(rs.getString("ProductName"));
                products.setPrice(rs.getDouble("price"));
                products.setImageLink(rs.getString("imageLink"));
                products.setImageList(rs.getString("imageList"));
                products.setQuantity(rs.getInt("Quantity"));
                products.setPriceSale(rs.getDouble("PriceSale"));
                products.setNote(rs.getInt("Note"));
                products.setSaleStatus(rs.getInt("SaleStatus"));
                products.setDescription(rs.getString("Description"));
                products.setView(rs.getInt("view"));
                products.setMetaDescription(rs.getString("MetaKeyWord"));
                products.setMetaTitle(rs.getString("MetaTitle"));
                products.setMetaDescription(rs.getString("MetaDescription"));
                products.setSlug(rs.getString("slug"));
                products.setCreateAt(rs.getDate("create_at"));
                products.setStatus(rs.getBoolean("status"));
                list.add(products);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public Boolean insertProducts(InsertProductModel insertProductModel) {
        insertProductModel.setMetaKeyWord(insertProductModel.getProductName());
        insertProductModel.setMetaTitle(insertProductModel.getProductName());
        insertProductModel.setMetaDescription(insertProductModel.getProductName());
        insertProductModel.setNote(1);
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        String abc = new Gson().toJson(insertProductModel);
        try {
            cs = conn.prepareCall("{call insertProducts (?)}");
            cs.setObject(1, abc);
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
//    private Long id;
//    private String productName;
//    private Double price;
//    private String imageLink;
//    private String imageList;
//    private Integer quantity;
//    private Double priceSale;
//    private Integer note;
//    private Integer saleStatus;
//    private String description;
//    private Integer view;
//    private String metaTitle;
//    private String metaKeyWord;
//    private String metaDescription;
//    private String slug;
//    private Date createAt;
//    private Boolean status;
    @Override
    public InsertProductModel findProductById(Integer id) {
        InsertProductModel products = new InsertProductModel();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findProductById (?)}");
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()){
                products.setId(rs.getLong("id"));
                products.setProductName(rs.getString("ProductName"));
                products.setPrice(rs.getDouble("price"));
                products.setImageLink(rs.getString("imageLink"));
                products.setImageList(rs.getString("imageList"));
                products.setQuantity(rs.getInt("quantity"));
                products.setPriceSale(rs.getDouble("priceSale"));
                products.setNote(rs.getInt("note"));
                products.setSaleStatus(rs.getInt("saleStatus"));
                products.setDescription(rs.getString("description"));
                products.setView(rs.getInt("view"));
                products.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
    return products;

    }

    @Override
    public Boolean editProduct(InsertProductModel insertProductModel) {
        String slug = MnpSlug.makeSlug(insertProductModel.getProductName());
        insertProductModel.setSlug(slug);
        insertProductModel.setNote(1);
        insertProductModel.setMetaTitle(insertProductModel.getProductName());
        insertProductModel.setMetaKeyWord(insertProductModel.getProductName());
        insertProductModel.setMetaDescription(insertProductModel.getProductName());
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        String json = new Gson().toJson(insertProductModel);
        try {
            cs = conn.prepareCall("{call editProduct (?)}");
            cs.setObject(1, json);
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
    public Boolean deleteProduct(Integer id) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call deleteProduct (?)}");
            cs.setInt(1, id);
            int i = cs.executeUpdate();
            if (i >0 ){
                bl= true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return bl;
    }

}
