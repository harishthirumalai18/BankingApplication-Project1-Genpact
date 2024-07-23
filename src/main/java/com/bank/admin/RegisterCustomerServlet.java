package com.bank.admin;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bank.util.DBConnection;

@WebServlet("/RegisterCustomerServlet")

public class RegisterCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String emailId = request.getParameter("emailId");
        String accountType = request.getParameter("accountType");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        String dateOfBirth = request.getParameter("dateOfBirth");
        String idProof = request.getParameter("idProof");

        if (initialBalance < 1000) {
            request.setAttribute("errorMessage", "Initial balance must be at least 1000.");
            request.getRequestDispatcher("registerCustomer.jsp").forward(request, response);
            return;
        }

        String accountNo = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        String tempPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 8);

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO customer (account_no, full_name, address, mobile_no, email_id, account_type, initial_balance, date_of_birth, id_proof, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, accountNo);
            stmt.setString(2, fullName);
            stmt.setString(3, address);
            stmt.setString(4, mobileNo);
            stmt.setString(5, emailId);
            stmt.setString(6, accountType);
            stmt.setDouble(7, initialBalance);
            stmt.setString(8, dateOfBirth);
            stmt.setString(9, idProof);
            stmt.setString(10, tempPassword);
            stmt.executeUpdate();

            request.setAttribute("accountNo", accountNo);
            request.setAttribute("tempPassword", tempPassword);
            request.getRequestDispatcher("customerRegistered.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error occurred. Please try again.");
            request.getRequestDispatcher("registerCustomer.jsp").forward(request, response);
        }
    }
}