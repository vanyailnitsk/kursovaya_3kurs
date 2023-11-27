<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Удаляем атрибуты сессии
    session.removeAttribute("userId");
    // Завершаем сессию
    session.invalidate();
    // Перенаправляем пользователя на страницу входа
    response.sendRedirect("login.jsp");
%>
