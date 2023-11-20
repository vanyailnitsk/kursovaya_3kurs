<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="/styles/navbar.css">
    <link rel="stylesheet" href="/styles/index.css">
</head>
<body>
    <nav class="navbar">
        <ul>
            <li><a href="/">Главная</a></li>
            <li><a href="/users">Пользователи</a></li>
        </ul>
    </nav>
    <h1><%= "Hello World!" %>
    </h1>
    <br/>
    <a href="users">Hello Servlet</a>
</body>
</html>