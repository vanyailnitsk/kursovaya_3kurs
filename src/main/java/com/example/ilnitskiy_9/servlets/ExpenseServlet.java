package com.example.ilnitskiy_9.servlets;

import com.example.ilnitskiy_9.model.Expense;
import com.example.ilnitskiy_9.service.db.ExpenseCategoryRepository;
import com.example.ilnitskiy_9.service.db.ExpenseRepository;
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

@WebServlet("/expense")
public class ExpenseServlet extends HttpServlet {
    private final ExpenseRepository expenseRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseServlet() {
        this.expenseRepository = new ExpenseRepository();
        this.expenseCategoryRepository = new ExpenseCategoryRepository();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        List<Expense> expenses = expenseRepository.getExpensesByUserId(userId);
        req.setAttribute("expenses",expenses);
        req.setAttribute("expense_categories",expenseCategoryRepository.getAllCategories());
        req.getRequestDispatcher("/expense-history.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Expense expense =  new Gson().fromJson(requestBody, Expense.class);
        if (expenseRepository.addExpense(expense)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message","No user with id "+expense.getUserId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)  {
        int expenseId = Integer.parseInt(req.getParameter("id"));
        if (!expenseRepository.deleteExpense(expenseId)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Expense expense =  new Gson().fromJson(requestBody, Expense.class);
        if (expenseRepository.editExpense(expense)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message","No expense with id "+expense.getId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }
}
