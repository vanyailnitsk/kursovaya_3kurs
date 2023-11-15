package com.example.java_lab4.servlets;

import com.example.java_lab4.model.Expense;
import com.example.java_lab4.service.db.ExpenseRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
}
