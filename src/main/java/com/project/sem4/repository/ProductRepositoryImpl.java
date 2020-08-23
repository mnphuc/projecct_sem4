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

}
