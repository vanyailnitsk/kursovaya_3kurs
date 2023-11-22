package com.example.java_lab4.servlets;

import com.example.java_lab4.model.Category;
import com.example.java_lab4.service.db.CategoryRepository;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/category")
public class CategoryServletREST extends HttpServlet {
    private final CategoryRepository categoryRepository;

    public CategoryServletREST() {
        this.categoryRepository = new CategoryRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryRepository.getAllCategories();
        Gson gson = new Gson();
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(categoryList));
    }
}
