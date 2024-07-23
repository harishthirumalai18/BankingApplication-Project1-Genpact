<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="com.bank.model.Transaction" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Transactions</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <style>
        body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(135deg, #FFA07A 10%, #20B2AA 100%);
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
    color: #333;
}

.container {
    max-width: 800px;
    width: 100%;
    margin: 50px auto;
    padding: 20px;
    background: rgba(255, 255, 255, 0.95);
    border-radius: 15px;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
    text-align: center;
    box-sizing: border-box;
    animation: fadeIn 1s ease-in-out;
    position: relative;
    overflow: hidden;
}

.container::before {
    content: '';
    position: absolute;
    top: -30px;
    right: -30px;
    bottom: -30px;
    left: -30px;
    background: linear-gradient(135deg, #72EDF2 10%, #5151E5 100%);
    z-index: -1;
    transform: rotate(15deg);
    border-radius: 30px;
    filter: blur(20px);
    opacity: 0.7;
}

h1 {
    font-size: 32px;
    margin-bottom: 20px;
    color: #333;
    font-weight: bold;
    position: relative;
}

h1::after {
    content: '';
    position: absolute;
    left: 50%;
    bottom: -10px;
    transform: translateX(-50%);
    width: 60px;
    height: 3px;
    background-color: #007bff;
    border-radius: 5px;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
    background-color: #fff;
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

th, td {
    padding: 12px;
    border-bottom: 1px solid #ddd;
    text-align: left;
}

th {
    background-color: #007bff;
    color: white;
    position: sticky;
    top: 0;
    font-weight: bold;
}

tr:hover {
    background-color: #f1f1f1;
}

a, form {
    display: inline-block;
    padding: 10px 20px;
    background-color: #007bff;
    color: white;
    text-decoration: none;
    border-radius: 5px;
    transition: background-color 0.3s ease;
    margin: 10px 5px;
    font-size: 16px;
    font-weight: bold;
}

a:hover, form:hover {
    background-color: #0056b3;
}

form {
    background: none;
    border: none;
    padding: 0;
    margin: 0;
    display: inline;
}

form input[type="submit"] {
    background-color: #007bff;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    font-size: 16px;
    font-weight: bold;
}

form input[type="submit"]:hover {
    background-color: #0056b3;
}

@keyframes fadeIn {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}

body::before {
    content: '';
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.05);
    z-index: -1;
}

.container::before {
    content: '';
    position: absolute;
    top: -10px;
    left: -10px;
    right: -10px;
    bottom: -10px;
    background: linear-gradient(135deg, #72EDF2 10%, #5151E5 100%);
    z-index: -2;
    border-radius: 15px;
    filter: blur(15px);
    opacity: 0.7;
}


    </style>
</head>
<body>
    <div class="container">
        <h1>Transactions</h1>
        <table>
            <tr>
                <th>Transaction ID</th>
                <th>Account No</th>
                <th>Amount</th>
                <th>Date</th>
                <th>Type</th>
                <th>Balance After Transaction</th>
            </tr>
            <%
                List<Transaction> transactions = (List<Transaction>) request.getAttribute("transaction");
                if (transactions != null) {
                    for (Transaction transaction : transactions) {
            %>
                <tr>
                    <td><%= transaction.getTransactionId() %></td>
                    <td><%= transaction.getAccountNo() %></td>
                    <td><%= transaction.getAmount() %></td>
                    <td><%= transaction.getTransactionDate() %></td>
                    <td><%= transaction.getType() %></td>
                    <td><%= transaction.getBalanceAfterTransaction() %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="6">No transactions found.</td>
                </tr>
            <%
                }
            %>
        </table>
        <form action="DownloadTransactionsServlet" method="post">
        <input type="submit" value="Download Transactions ">
    </form>
        
        <a href="customerDashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>