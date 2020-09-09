package com.project.sem4.repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.sem4.model.Products;
import com.project.sem4.model.view.InsertProductModel;
import com.project.sem4.repository.interfaces.ProductRepository;
import com.project.sem4.vendor.DBConnect;
import com.project.sem4.vendor.MnpSlug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Products> getAllProductByOder() {
        List<Products> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllProductByOder}");
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
    @Override
    public InsertProductModel findProductById(Long id) {
        InsertProductModel products = new InsertProductModel();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findProductById (?)}");
            cs.setLong(1, id);
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

    @Override
    public Products findProById(Long id) {
        Products products= new Products();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findProductsById (?)}");
            cs.setLong(1, id);
            rs = cs.executeQuery();
            while (rs.next()){
                products.setId(rs.getLong("id"));
                products.setProductName(rs.getString("productName"));
                products.setPrice(rs.getDouble("price"));
                products.setImageLink(rs.getString("imageLink"));
                products.setImageList(rs.getString("imageList"));
                products.setQuantity(rs.getInt("quantity"));
                products.setPriceSale(rs.getDouble("priceSale"));
                products.setNote(rs.getInt("note"));
                products.setSaleStatus(rs.getInt("saleStatus"));
                products.setDescription(rs.getString("description"));
                products.setView(rs.getInt("view"));
                products.setMetaTitle(rs.getString("metaTitle"));
                products.setMetaKeyWord(rs.getString("metaKeyWord"));
                products.setMetaDescription(rs.getString("metaDescription"));
                products.setSlug(rs.getString("slug"));
                products.setCreateAt(rs.getDate("create_At"));
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
    public Products getProductBySlug(String slug) {
        Products products= new Products();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getProductBySlug (?)}");
            cs.setString(1, slug);
            rs = cs.executeQuery();
            while (rs.next()){
                products.setId(rs.getLong("id"));
                products.setProductName(rs.getString("productName"));
                products.setPrice(rs.getDouble("price"));
                products.setImageLink(rs.getString("imageLink"));
                products.setImageList(rs.getString("imageList"));
                products.setQuantity(rs.getInt("quantity"));
                products.setPriceSale(rs.getDouble("priceSale"));
                products.setNote(rs.getInt("note"));
                products.setSaleStatus(rs.getInt("saleStatus"));
                products.setDescription(rs.getString("description"));
                products.setView(rs.getInt("view"));
                products.setMetaTitle(rs.getString("metaTitle"));
                products.setMetaKeyWord(rs.getString("metaKeyWord"));
                products.setMetaDescription(rs.getString("metaDescription"));
                products.setSlug(rs.getString("slug"));
                products.setCreateAt(rs.getDate("create_At"));
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
    public List<Products> getRelatedProduct(Double price) {
        List<Products> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getRelatedProduct (?)}");
            cs.setDouble(1, price);
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
    public List<Products> filterProductByPrice(Double minPrice, Double maxPrice) {
        List<Products> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call filterProductByPrice (?,?)}");
            cs.setDouble(1, minPrice);
            cs.setDouble(2, maxPrice);
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
}
