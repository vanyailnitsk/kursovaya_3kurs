<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.example.kursovaya.model.Category" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<html>
<head>
    <title>Аналитика</title>
    <link rel="stylesheet" href="/styles/userCabinet.css">
    <link rel="stylesheet" href="/styles/navbar.css">
    <link rel="stylesheet" href="/styles/main.css">
    <style>
        .chart-row {
            display: flex;
            justify-content: space-around;
        }

        .chart-container {
            width: 40%;
            text-align: center;
        }

        .chart-label {
            font-weight: bold;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <%
        Map<Category,Integer> expenseMap = (Map<Category, Integer>) request.getAttribute("expense_categories");
        Map<Category,Integer> incomeMap = (Map<Category, Integer>) request.getAttribute("income_categories");
    %>
    <jsp:include page="main.html"/>
    <div class="content chart-row">
        <div  class="chart-container">
            <p class="chart-label">Расходы</p>
            <canvas id="pieChart"></canvas>
        </div>
        <div   class="chart-container">
            <p class="chart-label">Доходы</p>
            <canvas id="pieChart1"></canvas>
        </div>
    </div>
    <script>
        let categoryMap = <%= new Gson().toJson(expenseMap) %>;
        let labels = Object.keys(categoryMap);
        let data = Object.values(categoryMap);
        let ctx = document.getElementById('pieChart').getContext('2d');
        let pieChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: labels,
                datasets: [{
                    data: data,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(255, 206, 86, 0.8)',
                        'rgba(75, 192, 192, 0.8)',
                        'rgba(153, 102, 255, 0.8)',
                        'rgba(255, 159, 64, 0.8)',
                        'rgba(0, 128, 0, 0.8)',
                        'rgba(128, 0, 128, 0.8)',
                    ],
                }],
            },
        });

        let categoryMapIncome = <%= new Gson().toJson(incomeMap) %>;
        let labelsIncome = Object.keys(categoryMapIncome);
        let dataIncome = Object.values(categoryMapIncome);
        let ctxIncome = document.getElementById('pieChart1').getContext('2d');
        let pieChartIncome = new Chart(ctxIncome, {
            type: 'doughnut',
            data: {
                labels: labelsIncome,
                datasets: [{
                    data: dataIncome,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(255, 206, 86, 0.8)',
                        'rgba(75, 192, 192, 0.8)',
                        'rgba(153, 102, 255, 0.8)',
                        'rgba(255, 159, 64, 0.8)',
                        'rgba(0, 128, 0, 0.8)',
                        'rgba(128, 0, 128, 0.8)',
                    ],
                }],
            },
        });
    </script>

</body>

</html>
