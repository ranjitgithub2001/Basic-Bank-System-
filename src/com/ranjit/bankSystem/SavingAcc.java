package com.ranjit.bankSystem;
import java.time.LocalDate;


public class SavingAcc extends Account{
	double minBalance;
	
	public SavingAcc() {
		this.minBalance=0;
	}

	public SavingAcc(long accno, String holderName, String accType, double balance) {
		super(accno,holderName,accType,balance);
		this.minBalance = 10000;
		this.setIntrestRate(2.70);

		
	}

	public double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}
	
	public void withdraw(double amt) {
		if(amt<=0)
		{
			System.out.println("invalid Amount");
		}
		else {
		double temp=this.balance-amt;
		if(temp>this.minBalance)
		{
			this.setBalance(temp);
			txInfo.add(new Tranxaction(balance, "Debit"));
			System.out.println("Withdrawl succesefull");
		}
		else
		{
			System.out.println("Withdrawal failed. Current balance cannot be lower than the minimum balance.");
		}
		}
	}

	@Override
	public double calculateInterest() {
		double result=(this.getBalance()*this.getIntrestRate()*1)/100;
		return result;
		
	}
	
	public void display()
	{
		System.out.println("Account no : "+this.getAccno());
		System.out.println("Account Holde name : "+this.getHolderName());
		System.out.println("Acccount type : "+this.AccType);
		System.out.println("Account Balance : "+this.getBalance());	
	}

}
