<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Withdraw Amount</title>
    <link rel="stylesheet" type="text/css" href="CSS/withdraw.css">
</head>
<body>
    <div class="container">
        <h1>Withdraw Amount</h1>
        <form action="WithdrawServlet" method="post">
            <label for="amount">Amount:</label>
            <input type="text" id="amount" name="initial_balance" required>
            <br><br>
            <button type="submit">Withdraw</button>
        </form>
        <a href="customerDashboard.jsp" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>
