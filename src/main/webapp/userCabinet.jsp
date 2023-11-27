<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="/styles/userCabinet.css">
    <link rel="stylesheet" href="/styles/navbar.css">
    <link rel="stylesheet" href="/styles/main.css">
</head>
<body>
    <jsp:include page="main.html"/>
    <div class="content">
        <h1>Добрый день, ${user.name}!</h1>
        <div class="categories-container">
            <c:forEach items="${categories}" var="category">
                <div class="category-card">
                    <img class="category-image" src="/img/category.png" alt="${category.name}">
                    <div class="category-title">${category.name}</div>
                    <div class="category-amount">1000 ₽</div>
                </div>
            </c:forEach>
        </div>
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
                        <button class="edit-button"
                                onclick="openEditExpenseModal(${expense.id},${expense.amount},'${expense.source}')"
                        >Изменить
                        </button>
                        <button class="delete-button" type="submit" onclick="deleteExpense(${expense.id})">Удалить
                        </button>
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

                <label for="source">Покупка:</label>
                <input type="text" id="source" name="source" maxlength="50" required>

                <label for="category_id">Категория:</label>
                <select id=category_id name="category_id">
                    <option value="0">Выберите категорию</option>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
                <button type="submit">Создать</button>
                <div id="message"></div>
            </form>
        </div>
    </div>
    <div id="editExpenseModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeEditExpenseModal()">&times;</span>
            <h2>Редактировать запись о покупке</h2>
            <form id="editExpenseForm">
                <input type="hidden" id="edit-user-id" name="user_id" required value="${user.id}">
                <input type="hidden" id="edit-expense-id" name="user_id" required>
                <label for="amount">Сумма:</label>
                <input type="number" id="edit-amount" name="amount" required value="">

                <label for="source">Покупка:</label>
                <input type="text" id="edit-source" name="source" maxlength="50" required>

                <label for="category_id">Категория:</label>
                <select id="edit-category-id" name="category_id">
                    <option value="0">Выберите категорию</option>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
                <button type="submit">Сохранить</button>
                <div id="edit-message"></div>
            </form>
        </div>
    </div>
    <script src="/scripts/userCabinet.js"></script>
</body>
</html>
