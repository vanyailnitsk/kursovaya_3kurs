package com.example.java_lab4.service.db;

import com.example.java_lab4.model.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {
    private final DataBaseService dataBaseService;
    static final String SELECT_BY_USERID = "SELECT * from expense where user_id=?";

    public ExpenseRepository() {
        this.dataBaseService = new DataBaseService();
    }
    public List<Expense> getExpensesByUserId(Integer userId) {
        List<Expense> expenses = new ArrayList<>();
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_USERID);
            statement.setInt(1, userId);
//            ResultSet resultSet = dataBaseService.select(SELECT_BY_USERID+userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Expense expense = new Expense(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("amount"),
                        resultSet.getString("source"),
                        resultSet.getInt("category"),
                        resultSet.getTimestamp("timestamp")
                );
                expenses.add(expense);
                System.out.println(expense.getSource());
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return expenses;
    }
}
