package com.example.ilnitskiy_9.servlets;

import com.example.ilnitskiy_9.model.Category;
import com.example.ilnitskiy_9.service.db.ExpenseRepository;
import com.example.ilnitskiy_9.service.db.IncomeRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/analytics")
public class AnalyticsServlet extends HttpServlet {
    private final ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;

    public AnalyticsServlet() {
        this.expenseRepository = new ExpenseRepository();
        this.incomeRepository = new IncomeRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        Map<Category,Integer> expenseMap = expenseRepository.getUserExpensesByCategories(userId);
        Map<Category,Integer> incomeMap = incomeRepository.getUserIncomesByCategories(userId);
        req.setAttribute("expense_categories",expenseMap);
        req.setAttribute("income_categories",incomeMap);
        req.getRequestDispatcher("/analytics.jsp").forward(req,resp);
    }
}
