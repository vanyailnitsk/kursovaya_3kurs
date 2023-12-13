package com.example.ilnitskiy_9.service.db;

import com.example.ilnitskiy_9.model.Category;
import com.example.ilnitskiy_9.model.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ExpenseRepository {
    private final DataBaseService dataBaseService;
    static final String SELECT_BY_USERID = "SELECT * from expense JOIN expense_category" +
            " on expense_category.id=expense.category where expense.user_id=? ORDER BY timestamp desc";
    static final String INSERT = "INSERT INTO expense (user_id,amount,source,category,timestamp) values (?,?,?,?,NOW())";
    static final String DELETE = "DELETE FROM expense where id=?";
    static final String UPDATE = "UPDATE expense SET amount=?,source=?,category=? where id=?";
    static final String SELECT_SUM_EXPENSES_OF_USER_GROUP_BY_CATEGORY =
            "SELECT expense_category.id, expense_category.name, COALESCE(SUM(amount), 0) AS sum" +
                    " FROM expense_category" +
                    " LEFT JOIN expense ON expense.category = expense_category.id AND expense.user_id=?" +
                    " GROUP BY expense_category.id, expense_category.name" +
                    " ORDER BY expense_category.id";

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
    public Map<Category,Integer> getUserExpensesByCategories(Integer userId) {
        List<Expense> expenses = new ArrayList<>();
        Connection conn = dataBaseService.getConnect();
        Map<Category,Integer> map = new LinkedHashMap<>();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_SUM_EXPENSES_OF_USER_GROUP_BY_CATEGORY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                Integer sumExpenses = resultSet.getInt("sum");
                map.put(category,sumExpenses);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
    public boolean addExpense(Expense expense) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(INSERT);
            statement.setInt(1, expense.getUserId());
            statement.setInt(2, expense.getAmount());
            statement.setString(3, expense.getSource());
            statement.setInt(4, expense.getCategoryId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean editExpense(Expense expense) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setInt(1, expense.getAmount());
            statement.setString(2, expense.getSource());
            statement.setInt(3, expense.getCategoryId());
            statement.setInt(4,expense.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean deleteExpense(Integer id) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(DELETE);
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
