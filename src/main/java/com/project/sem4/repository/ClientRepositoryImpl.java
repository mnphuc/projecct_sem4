package com.project.sem4.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.sem4.model.*;
import com.project.sem4.model.map.AttributeMap;
import com.project.sem4.model.view.RatingView;
import com.project.sem4.repository.interfaces.ClientRepository;
import com.project.sem4.vendor.DBConnect;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Repository
public class ClientRepositoryImpl implements ClientRepository {
    @Override
    public List<Categories> getCategoryByProId(Long proId) {
        List<Categories> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getCategoryByProId (?)}");
            cs.setLong(1, proId);
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
    public List<AttributeSet> getAttributeSetByProId(Long proId) {
        List<AttributeSet> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAttributeSetByProId (?)}");
            cs.setLong(1, proId);
            rs = cs.executeQuery();
            while (rs.next()){
                AttributeSet attributeSet = new AttributeSet();
                attributeSet.setId(rs.getInt("id"));
                attributeSet.setName(rs.getString("name"));
                attributeSet.setDescription(rs.getString("description"));
                list.add(attributeSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public List<Attribute> getAttributeByProId(Long proId,Integer attrId) {
        List<Attribute> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAttributeByProId(?,?)}");
            cs.setLong(1, proId);
            cs.setInt(2, attrId);
            rs = cs.executeQuery();
            while (rs.next()){
                Attribute attribute = new Attribute();
                attribute.setId(rs.getInt("id"));
                attribute.setName(rs.getString("name"));
                attribute.setDescription(rs.getString("description"));
                attribute.setAttributeSetId(rs.getInt("attributeSetId"));
                attribute.setDataType(rs.getString("dataType"));
                attribute.setDefaultValue(rs.getString("DefaultValue"));
                list.add(attribute);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public List<ProductAttribute> getAllAttributeByProId(Long proId) {
        List<ProductAttribute> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllAttributeByProId (?)}");
            cs.setLong(1, proId);
            rs = cs.executeQuery();
            while (rs.next()){
                ProductAttribute productAttribute = new ProductAttribute();
                productAttribute.setId(rs.getInt("id"));
                productAttribute.setAttributeId(rs.getInt("AttributeId"));
                productAttribute.setProductId(rs.getLong("productId"));
                list.add(productAttribute);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;

    }

    @Override
    public List<Products> getProductByCate(String url) {
        List<Products> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getProductByCate (?)}");
            cs.setString(1, url);
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
    public List<GsonOb> getAttributeByProId(Long proId) {
        List<GsonOb> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAttribute(?)}");
            cs.setLong(1, proId);
            rs = cs.executeQuery();
            String json = null;
            while (rs.next()){
                json = (String) rs.getObject("getAttributeByProId");
            }
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<GsonOb>>(){}.getType();
            list =new Gson().fromJson(json, type);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public Discount checkDiscount(String code) {
        Discount discount = new Discount();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findDiscountByCode (?)}");
            cs.setString(1, code);
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
    public List<RatingView> getRatingByProId(Long proId) {
        List<RatingView> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getRatingByProId (?)}");
            cs.setLong(1, proId);
            rs = cs.executeQuery();
            while (rs.next()){
                RatingView ratingView = new RatingView();
                ratingView.setId(rs.getInt("id"));
                ratingView.setStarRating(rs.getInt("starRating"));
                ratingView.setComment(rs.getString("comment"));
                ratingView.setNameUser(rs.getString("fullName"));
                ratingView.setCreateAt(rs.getDate("CreateAt"));
                list.add(ratingView);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public Boolean addRating(Rating rating) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call addRating (?,?,?,?)}");
            cs.setInt(1, rating.getStarRating());
            cs.setString(2, rating.getComment());
            cs.setLong(3, rating.getUserId());
            cs.setLong(4, rating.getProductId());
            int i = cs.executeUpdate();
            if (i > 0 ){
                bl = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return bl;
    }


    public class AttributeList {

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("AttributeSetId")
        @Expose
        private Integer attributeSetId;
        @SerializedName("Description")
        @Expose
        private String description;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("DataType")
        @Expose
        private String dataType;
        @SerializedName("DefaultValue")
        @Expose
        private String defaultValue;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getAttributeSetId() {
            return attributeSetId;
        }

        public void setAttributeSetId(Integer attributeSetId) {
            this.attributeSetId = attributeSetId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

    }


    public class GsonOb {

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Description")
        @Expose
        private String description;
        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("attributeList")
        @Expose
        private List<AttributeList> attributeList = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public List<AttributeList> getAttributeList() {
            return attributeList;
        }

        public void setAttributeList(List<AttributeList> attributeList) {
            this.attributeList = attributeList;
        }

    }

}
