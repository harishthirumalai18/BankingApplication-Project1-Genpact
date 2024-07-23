<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Registered</title>
    <link rel="stylesheet" type="text/css" href="CSS/customerRegistered.css">
</head>
<body>
    <div class="container">
        <div class="success-icon">&#10004;</div>
        <h2>Customer Registered Successfully</h2>
        <p>Account Number: ${accountNo}</p>
        <p>Temporary Password: ${tempPassword}</p>
        <p>Please provide this information to the customer for initial login.</p>
        <a href="adminDashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
