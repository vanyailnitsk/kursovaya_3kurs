package com.example.java_lab4.servlets;

import com.example.java_lab4.model.Expense;
import com.example.java_lab4.model.User;
import com.example.java_lab4.service.db.ExpenseCategoryRepository;
import com.example.java_lab4.service.db.ExpenseRepository;
import com.example.java_lab4.service.db.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
    private final UserRepository userRepository = new UserRepository();
    private final ExpenseCategoryRepository expenseCategoryRepository = new ExpenseCategoryRepository();
    private final ExpenseRepository expenseRepository = new ExpenseRepository();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null || path.isEmpty()) {
            List<User> users = userRepository.getAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
            return;
        }
        int userId = Integer.parseInt(path.split("/")[1]);
        User user = userRepository.getUserById(userId);
        List<Expense> expenses = expenseRepository.getExpensesByUserId(userId);
        req.setAttribute("expense_categories", expenseRepository.getUserExpensesByCategories(userId));
        req.setAttribute("user", user);
        req.setAttribute("expenses",expenses);
        req.getRequestDispatcher("/userCabinet.jsp").forward(req, resp);
    }
}
