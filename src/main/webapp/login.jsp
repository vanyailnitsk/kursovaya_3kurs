<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
    <link rel="stylesheet" href="/styles/login.css">
</head>
<body>
    <div class="login-container">
        <h2>Login Page</h2>
        <form action="/login" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <br>
            <button type="submit">Login</button>
        </form>
        <div class="error-message">${errorMessage}</div>
    </div>
</body>
</html>
