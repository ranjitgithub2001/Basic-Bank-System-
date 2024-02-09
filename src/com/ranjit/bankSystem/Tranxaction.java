package com.ranjit.bankSystem;
public class Tranxaction {
	double amount;
	String txType;
	public Tranxaction() {
		this.amount=0;
		this.txType=null;
	}
	public Tranxaction(double amount, String txType) {
		super();
		this.amount = amount;
		this.txType = txType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTxType() {
		return txType;
	}
	public void setTxType(String txType) {
		this.txType = txType;
	}
	
	
	
	
}
