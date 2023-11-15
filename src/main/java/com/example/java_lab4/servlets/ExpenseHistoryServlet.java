package com.example.java_lab4.servlets;

import com.example.java_lab4.model.Expense;
import com.example.java_lab4.model.User;
import com.example.java_lab4.service.db.ExpenseRepository;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/expense/*")
public class ExpenseHistoryServlet extends HttpServlet {
    private final ExpenseRepository expenseRepository;

    public ExpenseHistoryServlet() {
        this.expenseRepository = new ExpenseRepository();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null || path.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String userId = req.getPathInfo().split("/")[1];
        List<Expense> expenses = expenseRepository.getExpensesByUserId(Integer.parseInt(userId));
        req.setAttribute("expenses",expenses);
        req.getRequestDispatcher("/finance-history.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(requestBody);
        Expense expense =  new Gson().fromJson(requestBody, Expense.class);
        System.out.println(expense.getSource());
        System.out.println(expense);
    }
}
