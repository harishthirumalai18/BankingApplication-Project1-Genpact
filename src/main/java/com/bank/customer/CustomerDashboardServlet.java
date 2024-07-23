package com.bank.customer;
import com.bank.dao.CustomerDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/customerDashboard")

public class CustomerDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account_no") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String accountNo = (String) session.getAttribute("account_no");
        CustomerDAO customerDAO = new CustomerDAO();
        double currentBalance = 0.0;

        try {
            currentBalance = customerDAO.getCurrentBalance(accountNo);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
            return;
        }

        session.setAttribute("current_balance", currentBalance);
        request.getRequestDispatcher("customerDashboard.jsp").forward(request, response);
    }
}