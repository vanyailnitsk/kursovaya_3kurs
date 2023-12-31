package com.example.ilnitskiy_9.servlets;

import com.example.ilnitskiy_9.model.Category;
import com.example.ilnitskiy_9.service.db.IncomeCategoryRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IncomeCategoryServlet extends HttpServlet {
    private final IncomeCategoryRepository incomeCategoryRepository;

    public IncomeCategoryServlet() {
        this.incomeCategoryRepository = new IncomeCategoryRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = incomeCategoryRepository.getAllCategories();
        req.setAttribute("categories",categories);
        req.getRequestDispatcher("/categories.jsp").forward(req,resp);
    }
}
