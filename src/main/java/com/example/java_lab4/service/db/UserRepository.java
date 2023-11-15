package com.example.java_lab4.service.db;

import com.example.java_lab4.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final DataBaseService dataBaseService;
    static final String SELECT_ALL = "SELECT * from users;";
    static final String SELECT_BY_ID = "SELECT * from users where id=";

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
