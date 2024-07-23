package com.bank.model;
import java.time.LocalDate;

public class Customer {
	private int customerId;
    private String fullName;
    private String address;
    private String mobileNo;
    private String emailId;
    private String accountType;
    private double initialBalance;
    private LocalDate dateOfBirth;
    private String idProof;
    private String accountNo;
    private String password;
    private String accountStatus;
    private String balanceAfterTransaction;

    public Customer() {}

    public Customer(String fullName, String address, String mobileNo, String emailId, String accountType,
                    double initialBalance, LocalDate dateOfBirth, String idProof, String accountNo, String password) {
        this.fullName = fullName;
        this.address = address;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.dateOfBirth = dateOfBirth;
        this.idProof = idProof;
        this.accountNo = accountNo;
        this.password = password;
        this.accountStatus = "open";
        
    }

    // Getters and Setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

	public String getBalanceAfterTransaction() {
		return balanceAfterTransaction;
	}

	public void setBalanceAfterTransaction(String balanceAfterTransaction) {
		this.balanceAfterTransaction = balanceAfterTransaction;
	}


}
