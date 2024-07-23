package com.bank.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.CustomerDAO;
 @WebServlet("/CloseAccountServlet")
public class CloseAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account_no") == null) {
            response.sendRedirect("login.jsp");
            
            return;
        }

        String accountNo = (String) session.getAttribute("account_no");
        
        boolean isClosed = customerDAO.closeAccount(accountNo);

        if (isClosed) {
            session.invalidate();
            response.sendRedirect("accountClosed.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("closeAccount.jsp");
        
        
    }
}