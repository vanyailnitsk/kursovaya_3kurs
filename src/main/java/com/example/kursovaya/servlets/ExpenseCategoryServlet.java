package com.example.kursovaya.servlets;

import com.example.kursovaya.model.Category;
import com.example.kursovaya.service.db.ExpenseCategoryRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/expense-category")
public class ExpenseCategoryServlet extends HttpServlet {
    private final ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategoryServlet() {
        this.expenseCategoryRepository = new ExpenseCategoryRepository();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = expenseCategoryRepository.getAllCategories();
        req.setAttribute("categories",categories);
        req.getRequestDispatcher("/categories.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Category category = new Gson().fromJson(requestBody, Category.class);
        if (expenseCategoryRepository.addCategory(category)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error while creating category");
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("id"));
        if (!expenseCategoryRepository.deleteCategory(categoryId)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Category category = new Gson().fromJson(requestBody, Category.class);
        if (expenseCategoryRepository.editCategory(category)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message","No expense with id "+category.getId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }
}
