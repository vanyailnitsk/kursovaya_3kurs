package com.example.java_lab4.servlets;

import com.example.java_lab4.model.Expense;
import com.example.java_lab4.model.User;
import com.example.java_lab4.service.db.ExpenseRepository;
import com.example.java_lab4.service.db.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
    private final UserRepository userRepository = new UserRepository();
    private final ExpenseRepository expenseRepository = new ExpenseRepository();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null || path.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String userId = req.getPathInfo().split("/")[1];
        User user = userRepository.getUserById(Integer.parseInt(userId));
        List<Expense> expenses = expenseRepository.getExpensesByUserId(Integer.valueOf(userId));
        req.setAttribute("user", user);
        req.setAttribute("expenses",expenses);
        req.getRequestDispatcher("/userCabinet.jsp").forward(req, resp);
    }
}
