package com.example.ilnitskiy_9.servlets;

import com.example.ilnitskiy_9.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final LoginService loginService;

    public LoginServlet() {
        this.loginService = new LoginService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMessage", "");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int userId = loginService.auth(username, password);
        if (userId != -1) {
            req.getSession().setAttribute("user_id", userId);
            resp.sendRedirect( "/dashboard");
        } else {
            req.setAttribute("errorMessage", "error login or pass");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            super.doPost(req, resp);
        }
    }
}
