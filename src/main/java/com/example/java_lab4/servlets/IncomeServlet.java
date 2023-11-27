package com.example.java_lab4.servlets;

import com.example.java_lab4.model.Expense;
import com.example.java_lab4.model.Income;
import com.example.java_lab4.service.db.IncomeCategoryRepository;
import com.example.java_lab4.service.db.IncomeRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/income/*")
public class IncomeServlet extends HttpServlet {
    private final IncomeRepository incomeRepository;
    private final IncomeCategoryRepository incomeCategoryRepository;

    public IncomeServlet() {
        this.incomeRepository = new IncomeRepository();
        this.incomeCategoryRepository = new IncomeCategoryRepository();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null || path.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String userId = req.getPathInfo().split("/")[1];
        List<Income> incomes = incomeRepository.getIncomesByUserId(Integer.parseInt(userId));
        req.setAttribute("incomes",incomes);
        req.setAttribute("userId",userId);
        req.setAttribute("income_categories",incomeCategoryRepository.getAllCategories());
        req.getRequestDispatcher("/income-history.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Income income =  new Gson().fromJson(requestBody, Income.class);
        if (incomeRepository.addIncome(income)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message","No user with id "+income.getUserId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int incomeId = Integer.parseInt(req.getParameter("id"));
        if (!incomeRepository.deleteIncome(incomeId)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Income income =  new Gson().fromJson(requestBody, Income.class);
        if (incomeRepository.editIncome(income)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message","No income with id "+income.getId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }
}
