package com.ranjit.bankSystem;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {
	
	long Accno;
	String HolderName;
	String AccType;
	double balance;
	double intrestRate;
//	Tranxaction[] txInfo;
	List<Tranxaction> txInfo=new ArrayList<>();
	int txCount;
	double tempOpening;
	String AccStatus;
	
	
	
	public String getAccStatus() {
		return AccStatus;
	}


	public void setAccStatus(String accStatus) {
		AccStatus = accStatus;
	}


	public Account() {
		super();
	}


	public Account(long accno, String holderName, String accType, double balance) {
		super();
		Accno = accno;
		HolderName = holderName;
		AccType = accType;
		this.balance = balance;
		this.tempOpening=balance;
		this.AccStatus="Active";
		this.txCount= -1;
//		this.txInfo[++this.txCount] = new Tranxaction(balance, "Credit");
		
	}


	public double getTempOpening() {
		return tempOpening;
	}


	public void setTempOpening(double tempOpening) {
		this.tempOpening = tempOpening;
	}


	public long getAccno() {
		return Accno;
	}


	public void setAccno(long accno) {
		Accno = accno;
	}


	public String getHolderName() {
		return HolderName;
	}


	public void setHolderName(String holderName) {
		HolderName = holderName;
	}


	public String getAccType() {
		return AccType;
	}


	public void setAccType(String accType) {
		AccType = accType;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	public double getIntrestRate() {
		return intrestRate;
	}

	public void setIntrestRate(double intrestRate) {
		this.intrestRate = intrestRate;
	}
	

//	public Tranxaction[] getTxInfo() {
//		return txInfo;
//	}
//
//
//	public void setTxInfo(Tranxaction[] txInfo) {
//		this.txInfo = txInfo;
//	}


	public int getTxCount() {
		return txCount;
	}


	public void setTxCount(int txCount) {
		this.txCount = txCount;
	}


	public boolean deposit(double amt){
		this.balance+=amt;
		txInfo.add(new Tranxaction(amt, "Credit"));
//		this.txInfo[++this.txCount] = new Tranxaction(amt, "Credit");
		return true;
	}
public abstract void withdraw(double amt);

public abstract double calculateInterest();


public abstract void display();

}
