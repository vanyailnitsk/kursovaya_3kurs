package com.example.java_lab4.servlets;

import com.example.java_lab4.model.User;
import com.example.java_lab4.service.db.UserRepository;
import com.google.gson.Gson;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    private String message;
    private final UserRepository userRepository = new UserRepository();

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        Gson gson = new Gson();
        List<User> users = userRepository.getAllUsers();
        gson.toJson(users.get(0));
        out.println(gson.toJson(users));
        System.out.println(users);
    }

    public void destroy() {
    }
}