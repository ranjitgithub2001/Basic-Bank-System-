package com.ranjit.bankSystem;

public class CurrentAcc extends Account {
	double odLimit;
	

	public CurrentAcc(long accno, String holderName, String accType, double balance) {
		super(accno,holderName,accType,balance);
		this.odLimit = -15000;
		txInfo.add(new Tranxaction(balance, "Credit"));
	}

	public double getOdLimit() {
		return odLimit;
	}

	public void setOdLimit(double odLimit) {
		this.odLimit = odLimit;
	}
	
	public void withdraw(double amt) 
	{
		if (amt <= 0)
		{
	        System.out.println("Invalid withdrawal amount.");
	    } 
		else 
	    {
	        double newBalance = this.balance - amt;
	        if (newBalance < this.getOdLimit()) 
	        {
	            System.out.println("Overdraft limit exceeded.");
	        } 
	        else 
	        {
	            this.setBalance(newBalance);
	            txInfo.add(new Tranxaction(amt, "Debit"));
	            System.out.println("Withdrawal successful.");
	        }
	    }
	}

	@Override
	public double calculateInterest() {
		System.out.println("Bank does not provide any rate of intrest on Current Account");
		return 0;
	}
	public void display()
	{
		System.out.println("Account no : "+this.getAccno());
		System.out.println("Account Holde name : "+this.getHolderName());
		System.out.println("Acccount type : "+this.AccType);
		System.out.println("Maximum Overdraft Limit : "+this.getOdLimit());
		System.out.println("Account Balance : "+this.getBalance());
	}
	
	
}
