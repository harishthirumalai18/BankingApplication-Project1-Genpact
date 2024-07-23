package com.bank.customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.TransactionDAO;
import com.bank.model.Transaction;

@WebServlet("/ViewTransactionsServlet")
public class ViewTransactionsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("account_no") == null) {
            response.sendRedirect("login.jsp");
            
            return;
        }

        String accountNo = (String) session.getAttribute("account_no");
       
        TransactionDAO transactionDAO = new TransactionDAO();
        List<Transaction> transactions = null;
		try {
			transactions = transactionDAO.getTransactions(accountNo);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

        request.setAttribute("transaction", transactions);
        request.getRequestDispatcher("viewTransactions.jsp").forward(request, response);
    }
}