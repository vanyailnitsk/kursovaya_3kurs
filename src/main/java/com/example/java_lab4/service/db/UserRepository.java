package com.example.java_lab4.service.db;

import com.example.java_lab4.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final DataBaseService dataBaseService;
    static final String SELECT_ALL = "SELECT * from users;";
    static final String SELECT_BY_ID = "SELECT * from users where id=";
    private final String SELECT_AUTH = "SELECT id FROM users WHERE login = ? AND password = ?";

    public UserRepository() {
        this.dataBaseService = new DataBaseService();
    }
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = dataBaseService.select(SELECT_ALL);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public int getUserIdByUsernameAndPassword(String username,String password) {
        Connection conn = dataBaseService.getConnect();
        int userId=-1;
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_AUTH);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userId;
    }

    public User getUserById(int id) {
        try {
            ResultSet resultSet = dataBaseService.select(SELECT_BY_ID+id);
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            }
            else {
                throw new IllegalArgumentException("No user with id "+id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
