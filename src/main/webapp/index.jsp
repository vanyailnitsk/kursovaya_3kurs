<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>BonchMoney</title>
    <link rel="stylesheet" href="/styles/navbar.css">
    <link rel="stylesheet" href="/styles/index.css">
    <link rel="stylesheet" href="/styles/main.css">
</head>
<body>
    <jsp:include page="main.html" />
    <div class="content">
        <h1><%= "Добро пожаловать в приложение!" %>
        </h1>
        <br/>
        <a href="/users" >Перейти к списку пользователей</a>
    </div>
</body>
</html>