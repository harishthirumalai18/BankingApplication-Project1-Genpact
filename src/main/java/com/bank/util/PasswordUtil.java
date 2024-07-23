package com.bank.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
public class PasswordUtil {
	 private static final SecureRandom RANDOM = new SecureRandom();

	    public static String hashPassword(String password) throws NoSuchAlgorithmException {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hash = md.digest(password.getBytes());
	        return Base64.getEncoder().encodeToString(hash);
	    }

	    public static String generateTemporaryPassword() {
	        byte[] tempPassword = new byte[8];
	        RANDOM.nextBytes(tempPassword);
	        return Base64.getEncoder().encodeToString(tempPassword);
	    }
}
