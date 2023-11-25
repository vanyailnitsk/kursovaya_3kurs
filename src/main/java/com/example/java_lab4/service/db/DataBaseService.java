package com.example.java_lab4.service.db;

import java.sql.*;

public class DataBaseService {

    public Connection getConnect(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        final String url = "jdbc:postgresql://217.107.219.154:49307/bonch_2105188?characterEncoding=UTF-8";
        final String user = "bonch_2105188";
        final String password = "AcemoheRIIs=";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public ResultSet select(String sql){
        Statement statement = null;
        try {
            statement = getConnect().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return null;
        }
    }
}
