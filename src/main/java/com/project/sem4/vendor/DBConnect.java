package com.project.sem4.vendor;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DBConnect {
    public static Connection openConnect(){
        Connection conn =  null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=Shop_House", "sa", "1234$");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
//    public static void main(String[] args){
//        Date date = new Date();
//        Long mills = date.getTime();
//        System.out.println(mills);
//    }
//    public static void main(String[] args) {
//        Resource resource = new ClassPathResource("static/uploads");
//        File file = new File(resource.getFilename(), "acn.txt");
//        String static1 = DBConnect.class.getResource ("/static"). getFile ();
//        System.out.println(static1);
//
//        // get ALL file
//        try {
//            List<String> abc = Arrays.asList(resource.getFile().list());
//            for (String s : abc) {
//                System.out.println(s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public static void closeAll(Connection conn, CallableStatement ps, ResultSet rs){
        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (ps!=null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (rs!=null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
