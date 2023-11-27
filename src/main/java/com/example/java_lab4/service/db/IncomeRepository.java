package com.example.java_lab4.service.db;

import com.example.java_lab4.model.Category;
import com.example.java_lab4.model.Expense;
import com.example.java_lab4.model.Income;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class IncomeRepository {
    private final DataBaseService dataBaseService;
    static final String SELECT_BY_USERID = "SELECT * from income JOIN income_category" +
            " on income_category.id=income.category where income.user_id=? ORDER BY timestamp desc";
    static final String INSERT = "INSERT INTO income (user_id,amount,source,category,timestamp) values (?,?,?,?,NOW())";
    static final String DELETE = "DELETE FROM income where id=?";
    static final String UPDATE = "UPDATE income SET amount=?,source=?,category=? where id=?";
    static final String SELECT_SUM_EXPENSES_OF_USER_GROUP_BY_CATEGORY =
            "SELECT income_category.id, income_category.name, COALESCE(SUM(amount), 0) AS sum" +
                    " FROM income_category" +
                    " LEFT JOIN income ON income.category = income_category.id AND income.user_id=?" +
                    " GROUP BY income_category.id, income_category.name" +
                    " ORDER BY income_category.id";

    public IncomeRepository() {
        this.dataBaseService = new DataBaseService();
    }
    public List<Income> getIncomesByUserId(Integer userId) {
        List<Income> incomes = new ArrayList<>();
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_USERID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Income income = new Income(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("amount"),
                        resultSet.getString("source"),
                        resultSet.getInt("category"),
                        resultSet.getTimestamp("timestamp")
                );
                income.setCategory(resultSet.getString("name"));
                incomes.add(income);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomes;
    }
    public Map<Category,Integer> getUserIncomesByCategories(Integer userId) {
        List<Income> incomes = new ArrayList<>();
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
                Integer sumIncomes = resultSet.getInt("sum");
                map.put(category,sumIncomes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
    public boolean addIncome(Income income) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(INSERT);
            statement.setInt(1, income.getUserId());
            statement.setInt(2, income.getAmount());
            statement.setString(3, income.getSource());
            statement.setInt(4, income.getCategoryId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean editIncome(Income income) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setInt(1, income.getAmount());
            statement.setString(2, income.getSource());
            statement.setInt(3, income.getCategoryId());
            statement.setInt(4,income.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean deleteIncome(Integer id) {
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
