<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>История доходов/расходов</title>
    <link rel="stylesheet" href="/styles/finance-history.css">
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="/styles/main.css">
    <link rel="stylesheet" href="/styles/userCabinet.css">
</head>
<body>
    <jsp:include page="main.html"/>
    <div class="content">
        <%--        <h1>Таблица расходов</h1>--%>
        <%--        <table style="margin: auto">--%>
        <%--            <tr>--%>
        <%--                <th>Сумма</th>--%>
        <%--                <th>Описание</th>--%>
        <%--                <th>Категория</th>--%>
        <%--                <th>Дата</th>--%>
        <%--            </tr>--%>
        <%--            <c:forEach items="${expenses}" var="expense">--%>
        <%--            <tr>--%>
        <%--                <td>${expense.amount}</td>--%>
        <%--                <td>${expense.source}</td>--%>
        <%--                <td>${expense.category}</td>--%>
        <%--                <td>${expense.dateFormatted}</td>--%>
        <%--            </tr>--%>
        <%--            </c:forEach>--%>
        <%--        </table>--%>
        <div class="recent-purchases">
            <h2>Последние покупки</h2>
            <table>
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
    </div>
</body>
</html>
