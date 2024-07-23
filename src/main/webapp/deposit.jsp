<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deposit</title>
<link rel="stylesheet" type="text/css" href="CSS/deposit.css">
</head>
<body>
<div class="deposit-container">
    <h2>Deposit Money</h2>
    <form action="DepositServlet" method="post">
        <label for="amount">Amount:</label>
        <input type="number" id="amount" name="initial_balance" required>
        <input type="submit" value="Deposit" class="button">
    </form>
    <a href="customerDashboard.jsp" class="back-link">Back to Dashboard</a>
</div>
</body>
</html>
