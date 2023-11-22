package com.example.java_lab4.servlets;

import com.example.java_lab4.model.Category;
import com.example.java_lab4.model.Expense;
import com.example.java_lab4.service.db.CategoryRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    private final CategoryRepository categoryRepository;

    public CategoryServlet() {
        this.categoryRepository = new CategoryRepository();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryRepository.getAllCategories();
        req.setAttribute("categories",categories);
        req.getRequestDispatcher("/categories.jsp").forward(req,resp);
    }

//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        Expense expense =  new Gson().fromJson(requestBody, Expense.class);
//        if (expenseRepository.addExpense(expense)) {
//            resp.getWriter().println("Success");
//        }
//        else {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            JsonObject error = new JsonObject();
//            error.addProperty("message","No user with id "+expense.getUserId());
//            resp.setContentType("application/json");
//            resp.getWriter().write(error.toString());
//        }
//    }
//
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int expenseId = Integer.parseInt(req.getParameter("id"));
//        System.out.println(expenseId);
//        if (!expenseRepository.deleteExpense(expenseId)) {
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
//
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        Expense expense =  new Gson().fromJson(requestBody, Expense.class);
//        if (categoryRepository.editCategory()) {
//            resp.getWriter().println("Success");
//        }
//        else {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            JsonObject error = new JsonObject();
//            error.addProperty("message","No expense with id "+expense.getId());
//            resp.setContentType("application/json");
//            resp.getWriter().write(error.toString());
//        }
//    }
}
