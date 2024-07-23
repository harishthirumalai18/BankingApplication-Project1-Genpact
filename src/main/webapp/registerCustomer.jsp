<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Register Customer</title>
    <link rel="stylesheet" type="text/css" href="CSS/registerCustomer.css">
</head>
<body>
    <div class="form-container">
        <h2>Register Customer</h2>
        <form action="RegisterCustomerServlet" method="post">
            <div class="form-group">
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" required>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required>
            </div>
            <div class="form-group">
                <label for="mobileNo">Mobile No:</label>
                <input type="text" id="mobileNo" name="mobileNo" required>
            </div>
            <div class="form-group">
                <label for="emailId">Email ID:</label>
                <input type="email" id="emailId" name="emailId" required>
            </div>
            <div class="form-group">
                <label for="accountType">Account Type:</label>
                <select id="accountType" name="accountType" required>
                    <option value="Saving">Saving Account</option>
                    <option value="Current">Current Account</option>
                </select>
            </div>
            <div class="form-group">
                <label for="initialBalance">Initial Balance:</label>
                <input type="number" id="initialBalance" name="initialBalance" required min="1000">
            </div>
            <div class="form-group">
                <label for="dateOfBirth">Date of Birth:</label>
                <input type="date" id="dateOfBirth" name="dateOfBirth" required>
            </div>
            <div class="form-group">
                <label for="idProof">ID Proof:</label>
                <input type="text" id="idProof" name="idProof" required>
            </div>
            <input type="submit" value="Register" class="submit-button">
        </form>
        
        <div class="error-message">
            <c:if test="${not empty errorMessage}">
                ${errorMessage}
            </c:if>
        </div>
    </div>
</body>
</html>
