package com.bank.customer;
import com.bank.dao.CustomerDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

@WebServlet("/customerLogin")
public class CustomerLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account_no = request.getParameter("account_no");
        String password = request.getParameter("password");
        
        

        CustomerDAO customerDAO = new CustomerDAO();
        
        boolean isAuthenticated = customerDAO.authenticate(account_no, password);

		if (isAuthenticated) {
		    HttpSession session = request.getSession();
		    session.setAttribute("account_no", account_no);
		    
		    try {
                double currentBalance = customerDAO.getCurrentBalance(account_no);
                session.setAttribute("balance_after_transaction", currentBalance);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
                
                return;
            }
	

		   
		    response.sendRedirect("customerDashboard.jsp");
		} else {
		   
		    response.sendRedirect("login.jsp?error=1");
		}
    }
}