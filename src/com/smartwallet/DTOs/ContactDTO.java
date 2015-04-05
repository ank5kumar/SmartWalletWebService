package com.smartwallet.DTOs;

public class ContactDTO {
	
	private String userInputUserId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String mobileNumber;
	
	
	
	
	public ContactDTO(String userInputUserId, String firstName,
			String lastName, String emailId, String mobileNumber) {
		super();
		this.userInputUserId = userInputUserId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
	}
	public String getUserInputUserId() {
		return userInputUserId;
	}
	public void setUserInputUserId(String userInputUserId) {
		this.userInputUserId = userInputUserId;
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	

}
