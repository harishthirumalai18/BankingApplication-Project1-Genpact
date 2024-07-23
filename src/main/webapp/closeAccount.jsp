<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Close Account</title>
    <link rel="stylesheet" type="text/css" href="CSS/closeAccount.css">
</head>
<body>
    <div class="container">
        <h1>Close Account</h1>
        <form action="CloseAccountServlet" method="post">
            <p>Are you sure you want to close your account?</p>
            <button type="submit" class="yes-button">Yes, close my account</button>
        </form>
        <form action="customerDashboard.jsp" method="get">
            <button type="submit" class="no-button">No, take me back</button>
        </form>
    </div>
</body>
</html>
