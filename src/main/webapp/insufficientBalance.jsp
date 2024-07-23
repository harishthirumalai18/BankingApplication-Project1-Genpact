<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="insufficientBalance.css">
    <meta charset="UTF-8">
    <title>Insufficient Balance</title>
</head>
<body>
    <div class="container">
        <button class="close-btn" onclick="window.location.href='customerDashboard.jsp'">&times;</button>
        <br>
        <br>
        <h2>Insufficient Balance</h2>
        <p>Sorry, your account does not have enough balance to complete this transaction.</p>
        <a href="customerDashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
