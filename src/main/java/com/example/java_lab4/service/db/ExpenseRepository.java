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
    static final String SELECT_BY_USERID = "SELECT * from expense JOIN expense_category" +
            " on expense_category.id=expense.category where expense.user_id=? ORDER BY timestamp desc";
    static final String INSERT = "INSERT INTO expense (user_id,amount,source,category) values (?,?,?,?,NOW())";

    public ExpenseRepository() {
        this.dataBaseService = new DataBaseService();
    }
    public List<Expense> getExpensesByUserId(Integer userId) {
        List<Expense> expenses = new ArrayList<>();
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_USERID);
            statement.setInt(1, userId);
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
                expense.setCategory(resultSet.getString("name"));
                expenses.add(expense);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return expenses;
    }
    public boolean addExpense(Expense expense) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_USERID);
            statement.setInt(1, expense.getUserId());
            statement.setInt(2, expense.getAmount());
            statement.setString(3, expense.getSource());
            statement.setInt(4, expense.getCategoryId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
