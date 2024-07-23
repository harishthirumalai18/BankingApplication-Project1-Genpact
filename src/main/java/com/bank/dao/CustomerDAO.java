package com.bank.dao;
import com.bank.model.Customer;
import com.bank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class CustomerDAO {
	public void registerCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customer (full_name, address, mobile_no, email_id, account_type, initial_balance, date_of_birth, id_proof, account_no, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getMobileNo());
            ps.setString(4, customer.getEmailId());
            ps.setString(5, customer.getAccountType());
            ps.setDouble(6, customer.getInitialBalance());
            ps.setDate(7, java.sql.Date.valueOf(customer.getDateOfBirth()));
            ps.setString(8, customer.getIdProof());
            ps.setString(9, customer.getAccountNo());
            ps.setString(10, customer.getPassword());
            ps.executeUpdate();
        }
    }

	public Customer getCustomer(String account_no) throws SQLException {
        String query = "SELECT * FROM customer WHERE account_no = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, account_no);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(rs.getInt("customer_id"));
                    customer.setFullName(rs.getString("full_name"));
                    customer.setAddress(rs.getString("address"));
                    customer.setMobileNo(rs.getString("mobile_no"));
                    customer.setEmailId(rs.getString("email_id"));
                    customer.setAccountType(rs.getString("account_type"));
                    customer.setInitialBalance(rs.getDouble("initial_balance"));
                    customer.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                    customer.setIdProof(rs.getString("id_proof"));
                    customer.setAccountNo(rs.getString("account_no"));
                    customer.setPassword(rs.getString("password"));
                    customer.setAccountStatus(rs.getString("account_status"));
                    return customer;
                }
            }
        }
        return null;
    }

    public void updateCustomerPassword(String account_no, String newPassword) throws SQLException {
        String query = "UPDATE Customer SET password = ? WHERE account_no = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, newPassword);
            ps.setString(2, account_no);
            ps.executeUpdate();
        }
    }

   


    public boolean authenticate(String account_no, String password) {
        boolean isAuthenticated = false;
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM customer WHERE account_no = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, account_no);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                isAuthenticated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAuthenticated;
    
    }
    //DEPOSIT 
    
    public void deposit(String accountNo, double amount) throws SQLException {
        String updateAccountBalanceQuery = "UPDATE accounts SET balance = balance + ? WHERE accountNo = ?";
        String insertTransactionQuery = "INSERT INTO transaction (account_no, amount, transaction_date, type, balance_after_transaction) VALUES (?, ?, NOW(), ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement updateBalanceStmt = con.prepareStatement(updateAccountBalanceQuery);
             PreparedStatement insertTransactionStmt = con.prepareStatement(insertTransactionQuery)) {

            
            updateBalanceStmt.setDouble(1, amount);
            updateBalanceStmt.setString(2, accountNo);
            updateBalanceStmt.executeUpdate();

            
            double updatedBalance = getBalance(accountNo);

           
            insertTransactionStmt.setString(1, accountNo);
            insertTransactionStmt.setDouble(2, amount);
            insertTransactionStmt.setString(3, "deposit");
            insertTransactionStmt.setDouble(4, updatedBalance);
            insertTransactionStmt.executeUpdate();
        }
    }

    public double getBalance(String accountNo) throws SQLException {
        String query = "SELECT balance FROM accounts WHERE accountNo = ?";
        double balance = 0.0;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, accountNo);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    balance = rs.getDouble("balance");
                }
            }
        }

        return balance;
    }

    
    //WITHDRAW
    
    public boolean withdraw(String accountNo, double amount) throws SQLException {
        String updateAccountBalanceQuery = "UPDATE accounts SET balance = balance - ? WHERE accountNo = ? AND balance >= ?";
        String insertTransactionQuery = "INSERT INTO transaction (account_no, amount, transaction_date, type, balance_after_transaction) VALUES (?, ?, NOW(), ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement updateBalanceStmt = con.prepareStatement(updateAccountBalanceQuery);
             PreparedStatement insertTransactionStmt = con.prepareStatement(insertTransactionQuery)) {

            
            updateBalanceStmt.setDouble(1, amount);
            updateBalanceStmt.setString(2, accountNo);
            updateBalanceStmt.setDouble(3, amount); // Add this line
            int rowsAffected = updateBalanceStmt.executeUpdate();

            if (rowsAffected == 0) {
                
                return false;
            }

            
            double updatedBalance = getBalance(accountNo);

            
            insertTransactionStmt.setString(1, accountNo);
            insertTransactionStmt.setDouble(2, amount);
            insertTransactionStmt.setString(3, "withdrawal");
            insertTransactionStmt.setDouble(4, updatedBalance);
            insertTransactionStmt.executeUpdate();

            return true;
        }
    }

    public double getBalance1(String accountNo) throws SQLException {
        String query = "SELECT initial_balance FROM accounts WHERE account_no = ?";
        double balance = 0.0;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, accountNo);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    balance = rs.getDouble("initial_balance");
                }
            }
        }

        return balance;
    }


//CLOSE ACCOUNT
    
public boolean closeAccount(String accountNo) {
    boolean isClosed = false;
    String deleteTransactionsSQL = "DELETE FROM transaction WHERE account_no = ?";
    String deleteAccountSQL = "DELETE FROM accounts WHERE accountNo = ?";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement deleteTransactionsStmt = connection.prepareStatement(deleteTransactionsSQL);
         PreparedStatement deleteAccountStmt = connection.prepareStatement(deleteAccountSQL)) {

        connection.setAutoCommit(false);

        deleteTransactionsStmt.setString(1, accountNo);
        deleteTransactionsStmt.executeUpdate();

        deleteAccountStmt.setString(1, accountNo);
        int rowsAffected = deleteAccountStmt.executeUpdate();

        connection.commit();
        isClosed = (rowsAffected > 0);

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return isClosed;
}

//CURRENT BALANCE

public double getCurrentBalance(String accountNo) throws SQLException {
    double balance = 0.0;
    Connection connection = DBConnection.getConnection();
    String sql = "SELECT balance_after_transaction FROM transaction WHERE account_no = ? ORDER BY transaction_date DESC LIMIT 1";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, accountNo);
    ResultSet resultSet = statement.executeQuery();

    if (resultSet.next()) {
        balance = resultSet.getDouble("balance_after_transaction");
    }

    resultSet.close();
    statement.close();
    connection.close();
    
    return balance;
}

}


    


