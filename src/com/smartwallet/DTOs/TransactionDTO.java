package com.smartwallet.DTOs;

import java.sql.Date;



public class TransactionDTO {
	
	
	private String taker;
	private String giver;
	private int amount;
	private String timeOfTransaction;
	private long latitudeOfTransaction;
	private long longitutdeOfTransaction;
	
	public String getTaker() {
		return taker;
	}
	public void setTaker(String taker) {
		this.taker = taker;
	}
	public String getGiver() {
		return giver;
	}
	public void setGiver(String giver) {
		this.giver = giver;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getTimeOfTransaction() {
		return timeOfTransaction;
	}
	public void setTimeOfTransaction(String timeOfTransaction) {
		this.timeOfTransaction = timeOfTransaction;
	}
	public long getLatitudeOfTransaction() {
		return latitudeOfTransaction;
	}
	public void setLatitudeOfTransaction(long latitudeOfTransaction) {
		this.latitudeOfTransaction = latitudeOfTransaction;
	}
	public long getLongitutdeOfTransaction() {
		return longitutdeOfTransaction;
	}
	public void setLongitutdeOfTransaction(long longitutdeOfTransaction) {
		this.longitutdeOfTransaction = longitutdeOfTransaction;
	}
	public TransactionDTO(String taker, String giver, int amount,
			String timeOfTransaction) {
		super();
		this.taker = taker;
		this.giver = giver;
		this.amount = amount;
		this.timeOfTransaction = timeOfTransaction;
	}
	@Override
	public String toString() {
		return "TransactionDTO [taker=" + taker + ", giver=" + giver
				+ ", amount=" + amount + ", timeOfTransaction="
				+ timeOfTransaction + ", latitudeOfTransaction="
				+ latitudeOfTransaction + ", longitutdeOfTransaction="
				+ longitutdeOfTransaction + "]";
	}
	
	

}
