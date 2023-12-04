<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="/styles/login.css">
</head>
<body>
    <div class="login-container">
        <h2>Registration Page</h2>
        <form action="/register" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" maxlength="50" required>
            <br>
            <label for="login">Login:</label>
            <input type="text" id="login" name="login" maxlength="50" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" maxlength="50" required>
            <br>
            <button type="submit">Register</button>
            <div class="error-message">${errorMessage}</div>
        </form>
    </div>
</body>
</html>
