package com.example.ilnitskiy_9.service.db;

import com.example.ilnitskiy_9.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IncomeCategoryRepository {
    private final DataBaseService dataBaseService;
    static final String SELECT_ALL = "SELECT * FROM income_category ORDER BY id";
    static final String INSERT = "INSERT INTO income_category (name) values (?)";
    static final String DELETE = "DELETE FROM income_category where id=?";
    static final String UPDATE = "UPDATE income_category SET name=? where id=?";
    public IncomeCategoryRepository() {
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
