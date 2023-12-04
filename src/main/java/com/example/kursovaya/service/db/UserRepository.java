package com.example.kursovaya.service.db;

import com.example.kursovaya.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private final DataBaseService dataBaseService;
    static final String SELECT_BY_ID = "SELECT * from users where id=";
    private final String SELECT_AUTH = "SELECT id FROM users WHERE login = ? AND password = ?";
    private final String SELECT_USER_EXISTING = "SELECT COUNT(*) from users where login=?";
    private final String CREATE_USER = "INSERT INTO users (name, login, password) VALUES (?,?,?) RETURNING id";

    public UserRepository() {
        this.dataBaseService = new DataBaseService();
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
    public int createUser(String name,String login, String password) {
        Connection conn = dataBaseService.getConnect();
        int userId=-1;
        try {
            PreparedStatement statement = conn.prepareStatement(CREATE_USER);
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userId;
    }

    public boolean isUserExistsByLogin(String login) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_USER_EXISTING);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count") != 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
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
