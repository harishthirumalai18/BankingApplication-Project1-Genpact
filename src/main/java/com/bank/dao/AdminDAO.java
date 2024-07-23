package com.bank.dao;
import com.bank.model.Admin;
import com.bank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

	public class AdminDAO {
		public boolean authenticate(String username, String password) throws SQLException {
	        String query = "SELECT * FROM Admin WHERE username = ? AND password = ?";
	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(query)) {
	            ps.setString(1, username);
	            ps.setString(2, password);
	            try (ResultSet rs = ps.executeQuery()) {
	                return rs.next();
	            }
	        }
	    }

	    public void registerAdmin(Admin admin) throws SQLException {
	        String query = "INSERT INTO Admin (username, password) VALUES (?, ?)";
	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(query)) {
	            ps.setString(1, admin.getUsername());
	            ps.setString(2, admin.getPassword());
	            ps.executeUpdate();
	        }
	    }

	    
}
