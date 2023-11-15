<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="/styles/userCabinet.css">

</head>
<body>
    <div class="container">
        <h1>Добрый день, ${user.name}!</h1>
        <button class="create-button" onclick="openCreateExpenseModal()">Добавить покупку</button>
        <table>
            <caption>Ваши последние покупки</caption>
            <thead>
            <tr>
                <th>Дата</th>
                <th>Сумма</th>
                <th>Описание</th>
                <th>Категория</th>
                <th>Действие</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${expenses}" var="expense">
                <tr>
                    <td>${expense.dateFormatted}</td>
                    <td>${expense.amount}</td>
                    <td>${expense.source}</td>
                    <td>${expense.category}</td>
                    <td>
                        <button class="edit-button">Изменить</button>
                        <button class="delete-button">Удалить</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div id="createExpenseModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeCreateExpenseModal()">&times;</span>
            <h2>Добавить запись о покупке</h2>
            <form id="createExpenseForm">
                <input type="hidden" id="user_id" name="user_id" required value="${user.id}">
                <label for="amount">Сумма:</label>
                <input type="number" id="amount" name="amount" required>

                <label for="source">Источник:</label>
                <input type="text" id="source" name="source" maxlength="50" required>

                <label for="category_id">Категория:</label>
                <input type="number" id="category_id" name="category_id" required>

                <button type="submit">Создать</button>
            </form>
        </div>
    </div>
    <script src="/scripts/userCabinet.js"></script>
</body>
</html>
