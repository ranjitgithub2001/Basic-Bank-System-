package com.ranjit.bankSystem;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;





public class SalaryAcc extends Account {

	LocalDate accOpenDate;
	LocalDate lastTxDate;
	String EmployerName;
	Scanner sc=new Scanner(System.in);
	
	public SalaryAcc() {
		// TODO Auto-generated constructor stub
	}	
	
	
	
	public SalaryAcc(long accno, String holderName, String accType, double balance,LocalDate accOpenDate,String eName) {
		super(accno,holderName,accType,balance);
		this.accOpenDate = accOpenDate;
		this.lastTxDate = accOpenDate;
		this.EmployerName=eName;
		this.setIntrestRate(2.70);
		txInfo.add(new Tranxaction(balance, "Credit"));
	}

	

	public LocalDate getAccOpenDate() {
		return accOpenDate;
	}



	public void setAccOpenDate(LocalDate accOpenDate) {
		this.accOpenDate = accOpenDate;
	}



	public LocalDate getLastTxDate() {
		return lastTxDate;
	}



	public void setLastTxDate(LocalDate lastTxDate) {
		this.lastTxDate = lastTxDate;
	}

	public boolean isFrozen(LocalDate date2) {
		
		LocalDate date1=this.getLastTxDate();
		long DayDifference=ChronoUnit.DAYS.between(date1, date2);
		if( DayDifference>60){
			System.out.println("account is frozen");
			this.setAccStatus("Freezed");
			return true;
			
		}
		return false;
		
	}
public void deposit1(double amount)
{
	System.out.print("Enter the date (yyyy-MM-dd): ");
    String date1Str = sc.next();
    LocalDate date2 = LocalDate.parse(date1Str);
	
	if(!this.isFrozen(date2))
	{
		this.deposit(amount);
		this.setLastTxDate(date2);
	}
	else
	{
		this.deposit(amount);
		
	}
}

	public void withdraw(double amt) {
		System.out.print("Enter the first date (yyyy-MM-dd): ");
        String date1Str = sc.next();
        LocalDate date2 = LocalDate.parse(date1Str);
        
		if(!this.isFrozen(date2))
		{
			if (amt > 0) 
			{
	            if (balance >= amt) 
	            {
	                this.setBalance(this.getBalance()-amt);
	                txInfo.add(new Tranxaction(amt, "Debit"));
	                System.out.println("Withdrawn: " + amt);
	                return;
	            }
	            else 
	            {
	                System.out.println("Insufficient balance for withdrawal.");
	            }
	        } 
			else 
	        {
	            System.out.println("Invalid withdrawal amount.");
	        }
	        return;
			
		}
		else
		{
			System.out.println("Account is frozen. Withdrawal not allowed.");
			
		}	
	}



	@Override
	public double calculateInterest() {
		double result=(this.getBalance()*this.getIntrestRate()*1)/12;
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
