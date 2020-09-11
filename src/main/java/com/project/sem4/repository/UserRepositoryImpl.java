package com.project.sem4.repository;

import com.project.sem4.model.Role;
import com.project.sem4.model.Users;
import com.project.sem4.model.service.ConfirmationToken;
import com.project.sem4.model.view.InsertUser;
import com.project.sem4.repository.interfaces.UserRepository;
import com.project.sem4.vendor.DBConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import sun.security.x509.DNSName;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Users loadUserByUsername(String email) {
        Users users = new Users();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findUserByEmail (?)}");
            cs.setString(1, email);
            rs = cs.executeQuery();
            while (rs.next()){
                users.setUserID(rs.getLong("userID"));
                users.setFullName(rs.getString("FullName"));
                users.setBirthday(rs.getDate("birthday"));
                users.setPhoneNumber(rs.getString("PhoneNumber"));
                users.setEmail(rs.getString("email"));
                users.setAddress(rs.getString("address"));
                users.setPassword(rs.getString("password"));
                users.setLocked(rs.getBoolean("locked"));
                users.setEnabled(rs.getBoolean("enabled"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return users;
    }

    @Override
    public Boolean findUserByEmail(String email) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findUserByEmail (?)}");
            cs.setString(1, email);
            rs = cs.executeQuery();
            int i = 0;
            while (rs.next()){
                i++;
            }
            if (i == 0){
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
    public List<String> getRoleByUser(Long userId) {
        List<String> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getRoleByUser (?)}");
            cs.setLong(1, userId);
            rs = cs.executeQuery();
            while (rs.next()){
                String abc = rs.getString("roleName");
                list.add(abc);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;

    }

    @Override
    public List<String> getAllRole() {
        List<String> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getRoleByUser}");
            rs = cs.executeQuery();
            while (rs.next()){
                String abc = rs.getString("roleName");
                list.add(abc);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> list = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllRoles}");
            rs = cs.executeQuery();
            while (rs.next()){
               Role role = new Role();
               role.setRoleId(rs.getInt("roleId"));
               role.setRoleName(rs.getString("roleName"));
               list.add(role);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return list;
    }

    @Override
    public Boolean insertUser(InsertUser insertUser) {
        String pass =bCryptPasswordEncoder.encode(insertUser.getPassword());
        insertUser.setLocked(true);
        insertUser.setEnabled(true);
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call insertUser (?,?,?,?,?,?,?,?)}");
            cs.setString(1, insertUser.getFullName());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(insertUser.getBirthday());
            cs.setString(2, date);
            cs.setString(3, insertUser.getPhoneNumber());
            cs.setString(4,insertUser.getEmail());
            cs.setString(5, insertUser.getAddress());
            cs.setString(6, pass);
            cs.setBoolean(7, insertUser.getLocked());
            cs.setBoolean(8, insertUser.getEnabled());
            int i = cs.executeUpdate();
            if (i > 0){
                bl = true;
                Users users= getUserByEmail(insertUser.getEmail());
                if (insertUser.getRoleId() == null){
                    Integer[] integers = {2};
                    insertUser.setRoleId(integers);
                }
                for (Integer idRole : insertUser.getRoleId()){
                    insertRoleUser(users.getUserID(), idRole);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return bl;
    }

    @Override
    public Boolean insertUserClient(InsertUser insertUser) {
        String pass =bCryptPasswordEncoder.encode(insertUser.getPassword());
        insertUser.setLocked(false);
        insertUser.setEnabled(false);
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call insertUser (?,?,?,?,?,?,?,?)}");
            cs.setString(1, insertUser.getFullName());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(insertUser.getBirthday());
            cs.setString(2, date);
            cs.setString(3, insertUser.getPhoneNumber());
            cs.setString(4,insertUser.getEmail());
            cs.setString(5, insertUser.getAddress());
            cs.setString(6, pass);
            cs.setBoolean(7, insertUser.getLocked());
            cs.setBoolean(8, insertUser.getEnabled());
            int i = cs.executeUpdate();
            if (i > 0){
                bl = true;
                Users users= getUserByEmail(insertUser.getEmail());
                insertRoleUser(users.getUserID(), 2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return bl;
    }

    @Override
    public Boolean confirmUsers(Long userId, Boolean enabled) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call confirmUser(?,?)}");
            cs.setLong(1, userId);
            cs.setBoolean(2, enabled);
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
    public Users findUserById(Long id) {
        Users users = new Users();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call findUserById (?)}");
            cs.setLong(1, id);
            rs = cs.executeQuery();
            while (rs.next()){
                users.setUserID(rs.getLong("userID"));
                users.setFullName(rs.getString("FullName"));
                users.setBirthday(rs.getDate("birthday"));
                users.setPhoneNumber(rs.getString("PhoneNumber"));
                users.setEmail(rs.getString("email"));
                users.setAddress(rs.getString("address"));
                users.setPassword(rs.getString("password"));
                users.setLocked(rs.getBoolean("locked"));
                users.setEnabled(rs.getBoolean("enabled"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }
        return users;
    }

    @Override
    public Users getUserByEmail(String email) {
        Users users = new Users();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getUserByEmail (?)}");
            cs.setString(1, email);
            rs = cs.executeQuery();
            while (rs.next()){
                users.setUserID(rs.getLong("userID"));
                users.setFullName(rs.getString("FullName"));
                users.setBirthday(rs.getDate("birthday"));
                users.setPhoneNumber(rs.getString("PhoneNumber"));
                users.setEmail(rs.getString("email"));
                users.setAddress(rs.getString("address"));
                users.setPassword(rs.getString("password"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }

        return users;
    }

    @Override
    public Boolean insertRoleUser(Long idUser, Integer idRole) {
        Boolean bl = false;
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call insertRoleUser (?,?)}");
            cs.setLong(1,idUser);
            cs.setInt(2,idRole);
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
    public List<Users> getAllUser() {
        List<Users> list  = new ArrayList<>();
        Connection conn;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBConnect.openConnect();
        try {
            cs = conn.prepareCall("{call getAllUser}");
            rs = cs.executeQuery();
            while (rs.next()){
                Users users = new Users();
                users.setUserID(rs.getLong("userID"));
                users.setFullName(rs.getString("FullName"));
                users.setBirthday(rs.getDate("birthday"));
                users.setPhoneNumber(rs.getString("PhoneNumber"));
                users.setEmail(rs.getString("email"));
                users.setAddress(rs.getString("address"));
                users.setPassword(rs.getString("password"));
                list.add(users);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,cs,rs);
        }

        return list;
    }
}
