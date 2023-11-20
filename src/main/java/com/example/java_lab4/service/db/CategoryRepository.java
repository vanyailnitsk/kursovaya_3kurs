package com.example.java_lab4.service.db;

import com.example.java_lab4.model.Category;
import com.example.java_lab4.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private final DataBaseService dataBaseService;
    static final String SELECT_ALL = "SELECT * FROM expense_category";
    public CategoryRepository() {
        this.dataBaseService = new DataBaseService();
    }
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            ResultSet resultSet = dataBaseService.select(SELECT_ALL);
            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

}
