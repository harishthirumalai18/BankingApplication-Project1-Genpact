package com.bank.customer;

import com.bank.dao.CustomerDAO;
import com.bank.dao.TransactionDAO;
import com.bank.model.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account_no") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String accountNo = (String) session.getAttribute("account_no");
        String amountStr = request.getParameter("initial_balance");

        if (amountStr == null || amountStr.trim().isEmpty()) {
            response.sendRedirect("error.jsp");
            
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp");
            
            return;
        }

        CustomerDAO customerDAO = new CustomerDAO();
        try {
            double currentBalance = customerDAO.getCurrentBalance(accountNo);
            if (currentBalance < amount) {
                response.sendRedirect("insufficientBalance.jsp");
                return;
            }

            customerDAO.withdraw(accountNo, amount);
            
            double newBalance = customerDAO.getCurrentBalance(accountNo); 

            Transaction transaction = new Transaction();
            transaction.setAccountNo(accountNo);
            transaction.setAmount(amount);
            transaction.setTransactionDate(new Date());
            transaction.setType("withdraw");
            transaction.setBalanceAfterTransaction(newBalance);

            TransactionDAO.addTransaction(transaction);

            session.setAttribute("current_balance", newBalance);
            
            response.sendRedirect("WithdrawSuccessful.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("withdraw.jsp");
    }
}