package com.example.kursovaya.servlets;

import com.example.kursovaya.model.Expense;
import com.example.kursovaya.model.User;
import com.example.kursovaya.service.db.ExpenseCategoryRepository;
import com.example.kursovaya.service.db.ExpenseRepository;
import com.example.kursovaya.service.db.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class UserServlet extends HttpServlet {
    private final UserRepository userRepository = new UserRepository();
    private final ExpenseCategoryRepository expenseCategoryRepository = new ExpenseCategoryRepository();
    private final ExpenseRepository expenseRepository = new ExpenseRepository();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        User user = userRepository.getUserById(userId);
        List<Expense> expenses = expenseRepository.getExpensesByUserId(userId);
        req.setAttribute("expense_categories", expenseRepository.getUserExpensesByCategories(userId));
        req.setAttribute("user", user);
        req.setAttribute("expenses",expenses);
        req.getRequestDispatcher("/userCabinet.jsp").forward(req, resp);
    }
}