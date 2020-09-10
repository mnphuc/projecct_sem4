package com.project.sem4.repository;

import com.project.sem4.model.AddressCities;
import com.project.sem4.model.AddressDistrict;
import com.project.sem4.model.AddressWards;
import com.project.sem4.model.view.AddressDistrictView;
import com.project.sem4.model.view.AddressWardView;
import com.project.sem4.repository.interfaces.AddressRepository;
import com.project.sem4.vendor.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.project.sem4.vendor.DBConnect.closeAll;
import static com.project.sem4.vendor.DBConnect.openConnect;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
    @Override
    public List<AddressCities> getAllCity() {
        List<AddressCities> list = new ArrayList<>();
        Connection conn;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        conn = openConnect();
        try {
            cstmt = conn.prepareCall("{call get_all_Cities}");
            rs = cstmt.executeQuery();
            while (rs.next()){
                AddressCities cities = new AddressCities();
                cities.setId(rs.getInt(1));
                cities.setName(rs.getString(2));
                cities.setType(rs.getString(3));
                list.add(cities);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            DBConnect.closeAll(conn, cstmt, rs);
        }
        return list;
    }
    @Override
    public List<AddressDistrictView> getAllDistrict() {
        List<AddressDistrictView> list = new ArrayList<>();
        Connection conn;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cstmt = conn.prepareCall("{call get_all_District}");
            rs = cstmt.executeQuery();
            while (rs.next()){
                AddressDistrictView district = new AddressDistrictView();
                district.setId(rs.getInt(1));
                district.setName(rs.getString(2));
                district.setType(rs.getString(3));
                district.setCityName(rs.getString(5));
                list.add(district);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public Boolean insertDistrict(AddressDistrict district) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = openConnect();

        try {
            cs = conn.prepareCall("{call create_district (?,?,?,?)}");
            cs.setInt(1, district.getId());
            cs.setString(2, district.getName());
            cs.setString(3, district.getType());
            cs.setInt(4, district.getCitiesId());
            int i = cs.executeUpdate();
            if (i > 0){
                bl = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            closeAll(conn,cs,rs);
        }
        return bl;
    }

    @Override
    public Boolean deleteCities(Integer Id) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();

        try {
            cstmt = conn.prepareCall("{call Delete_Cities (?)}");
            cstmt.setInt(1, Id);
            int i = cstmt.executeUpdate();
            if (i>0){
                bl = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cstmt,rs);
        }
        return bl;
    }


    @Override
    public List<AddressDistrict> findDistrictByCitiesId(Integer Id) {
        List<AddressDistrict> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = openConnect();
        try {
            cs = conn.prepareCall("{call Find_District_by_CitiesId (?)}");
            cs.setInt(1, Id);
            rs = cs.executeQuery();
            while (rs.next()){
                AddressDistrict district = new AddressDistrict();
                district.setId(rs.getInt(1));
                district.setName(rs.getString(2));
                district.setType(rs.getString(3));
                district.setCitiesId(rs.getInt(4));
                list.add(district);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public AddressDistrictView findDistrictById(Integer Id) {
        AddressDistrictView district = null;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = openConnect();
        try {
            cs = conn.prepareCall("{call findDistrictById (?)}");
            cs.setInt(1, Id);
            rs = cs.executeQuery();
            while (rs.next()){
                district = new AddressDistrictView();
                district.setId(rs.getInt("id"));
                district.setName(rs.getString("name"));
                district.setType(rs.getString("type"));
                district.setCitiesId(rs.getInt("CitiesId"));
                district.setCityName(rs.getString("CityName"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            closeAll(conn,cs,rs);
        }
       return district;
    }

    @Override
    public Boolean editDistrict(AddressDistrict district) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;

        conn = openConnect();

        try {
            cs = conn.prepareCall("{call editDistrict (?,?,?,?)}");
            cs.setInt(1 ,district.getId());
            cs.setString(2, district.getName());
            cs.setString(3, district.getType());
            cs.setInt(4, district.getCitiesId());
            int i = cs.executeUpdate();
            if (i > 0){
                bl = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll(conn,cs,rs);
        }

        return bl;
    }

    @Override
    public Boolean deletetDistrict(Integer Id) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = openConnect();
        try {
            cs = conn.prepareCall("{call deleteDistrict (?)}");
            cs.setInt(1, Id);
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
    public List<AddressWards> findWardByDistrictId(Integer districtId) {
        List<AddressWards> addressWards = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = openConnect();

        try {
            cs = conn.prepareCall("{call findWardByDistrictId (?)}");
            cs.setInt(1, districtId);
            rs = cs.executeQuery();
            while (rs.next()){
                AddressWards wards = new AddressWards();
                wards.setId(rs.getInt("Id"));
                wards.setName(rs.getString("name"));
                wards.setType(rs.getString("type"));
                wards.setDistrictId(rs.getInt("DistrictId"));
                addressWards.add(wards);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll(conn,cs,rs);
        }
        return addressWards;
    }

    @Override
    public Boolean editCities(AddressCities cities) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = openConnect();
        try {
            cs = conn.prepareCall("{call editCities (?,?,?)}");
            cs.setInt(1, cities.getId());
            cs.setString(2, cities.getName());
            cs.setString(3, cities.getType());
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
    public List<AddressWardView> getAllWards() {
        List<AddressWardView> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = openConnect();
        try {
            cs = conn.prepareCall("{call getAllWard }");
            rs = cs.executeQuery();
            while (rs.next()){
                AddressWardView wardView = new AddressWardView();
                wardView.setId(rs.getInt("id"));
                wardView.setName(rs.getString("name"));
                wardView.setType(rs.getString("type"));
                wardView.setDistrictId(rs.getInt("DistrictId"));
                wardView.setDistrictName(rs.getString("DistrictName"));
                list.add(wardView);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public Boolean insertCities(AddressCities cities) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cstmt = null;
        ResultSet resultSet = null;
        conn = DBConnect.openConnect();
        try {
            cstmt = conn.prepareCall("{call create_cities (?,?,?)}");
            cstmt.setInt(1, cities.getId());
            cstmt.setString(2, cities.getName());
            cstmt.setString(3, cities.getType());
            int i = cstmt.executeUpdate();
            if (i > 0){
                return bl = true;
            }
        } catch (SQLException ex) {
            ex.getErrorCode();
        }finally {
            DBConnect.closeAll(conn,cstmt,resultSet);
        }
        return bl;
    }

    @Override
    public AddressCities findCitiesById(Integer Id) {
        AddressCities cities = null;
        Boolean bl =false;
        Connection conn;
        CallableStatement cstmt = null;
        ResultSet resultSet = null;
        conn = DBConnect.openConnect();
        try {
            cstmt = conn.prepareCall("{call find_city_by_id (?)}");
            cstmt.setInt(1 ,Id);
            resultSet = cstmt.executeQuery();
            while (resultSet.next()){
               cities = new AddressCities();
               cities.setId(resultSet.getInt("Id"));
               cities.setName(resultSet.getString("name"));
               cities.setType(resultSet.getString("type"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cstmt,resultSet);
        }
        return cities;
    }

    @Override
    public Boolean insertWard(AddressWards wards) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = openConnect();
        try {
            cs = conn.prepareCall("{call insertWard (?,?,?,?)}");
            cs.setInt(1, wards.getId());
            cs.setString(2, wards.getName());
            cs.setString(3, wards.getType());
            cs.setInt(4, wards.getDistrictId());
            int i = cs.executeUpdate();
            if (i > 0){
                bl = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll(conn,cs,rs);
        }
        return bl;
    }

    @Override
    public AddressWardView findWardById(Integer Id) {
        AddressWardView wardView = new AddressWardView();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;

        conn = openConnect();

        try {
            cs = conn.prepareCall("{call findWardById (?)}");
            cs.setInt(1, Id);
            rs = cs.executeQuery();
            while (rs.next()){
                wardView.setId(rs.getInt("id"));
                wardView.setName(rs.getString("name"));
                wardView.setType(rs.getString("type"));
                wardView.setDistrictId(rs.getInt("DistrictId"));
                wardView.setDistrictName(rs.getString("DistrictName"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return wardView;
    }

    @Override
    public Boolean updateWards(AddressWards wards) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = openConnect();
        try {
            cs = conn.prepareCall("{call updateWards (?,?,?,?)}");
            cs.setInt(1, wards.getId());
            cs.setString(2, wards.getName());
            cs.setString(3, wards.getType());
            cs.setInt(4, wards.getDistrictId());
            int i = cs.executeUpdate();
            if (i>0){
                bl = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll(conn,cs,rs);
        }
        return bl;
    }

}
