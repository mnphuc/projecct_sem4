package com.project.sem4.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.sem4.model.Attribute;
import com.project.sem4.model.AttributeSet;
import com.project.sem4.model.view.AttributeSetAttributeView;
import com.project.sem4.repository.interfaces.AttributeRepository;
import com.project.sem4.vendor.DBConnect;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class AttributeRepositoryImpl implements AttributeRepository {
    @Override
    public List<AttributeSet> getAllAttributeSet() {
        List<AttributeSet> attributeSets = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllAttributeSet}");
            rs = cs.executeQuery();
            while (rs.next()){
                AttributeSet attributeSet = new AttributeSet();
                attributeSet.setId(rs.getInt("id"));
                attributeSet.setName(rs.getString("name"));
                attributeSet.setDescription(rs.getString("description"));
                attributeSets.add(attributeSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }

        return attributeSets;
    }

    @Override
    public Boolean insertAttributeSet(AttributeSet attributeSet) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call createAttributeSet (?,?)}");
            cs.setString(1, attributeSet.getName());
            cs.setString(2, attributeSet.getDescription());
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
    public AttributeSet findAttributeSetById(Integer id) {
        AttributeSet attributeSet = new AttributeSet();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findAttributeSetById (?)}");
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()){
                attributeSet.setId(rs.getInt("id"));
                attributeSet.setName(rs.getString("name"));
                attributeSet.setDescription(rs.getString("description"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return attributeSet;
    }

    @Override
    public Boolean updateAttributeSet(AttributeSet attributeSet) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn =DBConnect.openConnect();

        try {
            cs = conn.prepareCall("{call updateAttributeSet (?,?,?)}");
            cs.setInt(1, attributeSet.getId());
            cs.setString(2, attributeSet.getName());
            cs.setString(3, attributeSet.getDescription());
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
    public Boolean deleteAttributeSet(Integer id) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call deleteAttributeSet (?)}");
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

    @Override
    public List<Attribute> getAllAttribute() {
        List<Attribute> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllAttribute}");
            rs = cs.executeQuery();
            while (rs.next()){
                Attribute attribute= new Attribute();
                attribute.setId(rs.getInt("id"));
                attribute.setAttributeSetId(rs.getInt("AttributeSetId"));
                attribute.setDescription(rs.getString("description"));
                attribute.setName(rs.getString("name"));
                attribute.setDataType(rs.getString("dataType"));
                attribute.setDefaultValue(rs.getString("defaultValue"));
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
    public List<Attribute> findAttributeByAttributeSetId(Integer id) {
        List<Attribute> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findAttributeByAttributeSetId (?)}");
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()){
                Attribute attribute= new Attribute();
                attribute.setId(rs.getInt("id"));
                attribute.setAttributeSetId(rs.getInt("AttributeSetId"));
                attribute.setDescription(rs.getString("description"));
                attribute.setName(rs.getString("name"));
                attribute.setDataType(rs.getString("dataType"));
                attribute.setDefaultValue(rs.getString("defaultValue"));
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
    public Boolean createAttribute(Attribute attribute) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call createAttribute(?,?,?,?,?)}");
            cs.setInt(1, attribute.getAttributeSetId());
            cs.setString(2, attribute.getDescription());
            cs.setString(3, attribute.getName());
            cs.setString(4, attribute.getDataType());
            cs.setString(5, attribute.getDefaultValue());
            int i = cs.executeUpdate();
            if ( i> 0 ){
                bl=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return bl;
    }

    @Override
    public Boolean updateAttribute(Attribute attribute) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call updateAttribute (?,?,?,?,?,?)}");
            cs.setInt(1,attribute.getId());
            cs.setInt(2, attribute.getAttributeSetId());
            cs.setString(3, attribute.getDescription());
            cs.setString(4, attribute.getName());
            cs.setString(5, attribute.getDataType());
            cs.setString(6, attribute.getDefaultValue());
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
    public Attribute findAttributeById(Integer id) {
        Attribute attribute = new Attribute();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn= DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findAttributeById(?)}");
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()){
                attribute.setId(rs.getInt("id"));
                attribute.setAttributeSetId(rs.getInt("AttributeSetId"));
                attribute.setName(rs.getString("name"));
                attribute.setDescription(rs.getString("description"));
                attribute.setDataType(rs.getString("dataType"));
                attribute.setDefaultValue(rs.getString("defaultValue"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return attribute;
    }

    @Override
    public Boolean deleteAttribute(Integer id) {
        Boolean bl =false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call deleteAttribute (?)}");
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

    @Override
    public String getListAttributeSetAttributeViews() {
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        String json = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call returnJson}");
            rs = cs.executeQuery();
            while (rs.next()){
                json = (String) rs.getObject("AttributeJson");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

        }
        return json;
    }

}
