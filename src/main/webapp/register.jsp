<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="/styles/login.css">
</head>
<body>
    <div class="login-container">
        <h2>Форма регистрации</h2>
        <form action="/register" method="post">
            <label for="name">Имя:</label>
            <input type="text" id="name" name="name" maxlength="50" required>
            <br>
            <label for="login">Логин:</label>
            <input type="text" id="login" name="login" maxlength="50" required>
            <br>
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" maxlength="50" required>
            <br>
            <button type="submit">Register</button>
            <p>Уже зарегистрированы? &nbsp;<a href="/login">Вход</a></p>
        </form>
        <div class="error-message">${errorMessage}</div>
    </div>
</body>
</html>
