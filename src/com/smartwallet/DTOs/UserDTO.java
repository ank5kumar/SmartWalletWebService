package com.smartwallet.DTOs;

import java.io.Serializable;

public class UserDTO implements Serializable {
	 int userId ;//gjg
	 String userinputuserId ;
	 public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserinputuserId() {
		return userinputuserId;
	}
	public void setUserinputuserId(String userinputuserId) {
		this.userinputuserId = userinputuserId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPasswordForLogin() {
		return passwordForLogin;
	}
	public void setPasswordForLogin(String passwordForLogin) {
		this.passwordForLogin = passwordForLogin;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	String firstName ;
	 String lastName ;
	 String emailId ;
	 String passwordForLogin ;
	 String mobileNumber ;
	 
	public UserDTO(int userId2, String userinputuserId, String firstName,
			String lastName, String emailId, String passwordForLogin,
			String mobileNumber) {
		super();
		this.userId = userId2;
		this.userinputuserId = userinputuserId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.passwordForLogin = passwordForLogin;
		this.mobileNumber = mobileNumber;
	}
	 

}
