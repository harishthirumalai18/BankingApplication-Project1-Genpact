package com.bank.admin;

import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;
import com.bank.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/adminRegisterCustomer")

public class AdminRegisterCustomerServlet extends HttpServlet {
   
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String emailId = request.getParameter("emailId");
        String accountType = request.getParameter("accountType");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String idProof = request.getParameter("idProof");

        String accountNo = generateAccountNo();
        String tempPassword = PasswordUtil.generateTemporaryPassword();

        try {
            String hashedPassword = PasswordUtil.hashPassword(tempPassword);

            Customer customer = new Customer(fullName, address, mobileNo, emailId, accountType, initialBalance, dateOfBirth, idProof, accountNo, hashedPassword);
            CustomerDAO customerDAO = new CustomerDAO();
            customerDAO.registerCustomer(customer);

           

            response.sendRedirect("adminDashboard.jsp?registrationSuccess=1");
        } catch (NoSuchAlgorithmException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private String generateAccountNo() {
        
        return "ACC" + System.currentTimeMillis();
    }
}