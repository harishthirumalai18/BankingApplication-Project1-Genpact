package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import com.bank.model.Transaction;
import com.bank.util.DBConnection;
public class TransactionDAO {
	public static boolean addTransaction(Transaction transaction) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO transaction (account_no, amount, transaction_date, type, balance_after_transaction) VALUES (?, ?, ?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, transaction.getAccountNo());
            ps.setDouble(2, transaction.getAmount());
            ps.setDate(3, new java.sql.Date(transaction.getTransactionDate().getTime()));
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Transaction> getTransactions(String accountNo) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM transaction WHERE account_no = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, accountNo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setAccountNo(rs.getString("account_no"));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setTransactionDate(rs.getDate("transaction_date"));
                transaction.setType(rs.getString("type"));
                transaction.setBalanceAfterTransaction(rs.getDouble("balance_after_transaction"));

                transactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
    
    
}