package com.ranjit.bankSystem;

import java.util.Scanner;



public class LoanAcc extends Account {
double loanAmt;
int tenureMonths;
double emiAmt;
	
	public LoanAcc() {
		this.loanAmt=0;
	}
	Scanner sc=new Scanner(System.in);
	public LoanAcc(long accno, String holderName, String accType, double balance,int tm) {
		super(accno,holderName,accType,(-balance));
		this.loanAmt = balance;
		this.setIntrestRate(12);
		this.tenureMonths=tm;
		txInfo.add(new Tranxaction(balance, "Credit"));
		
	}
	

	public double getEmiAmt() {
		return emiAmt;
	}


	public void setEmiAmt(double emiAmt) {
		this.emiAmt = emiAmt;
	}


	public Scanner getSc() {
		return sc;
	}


	public void setSc(Scanner sc) {
		this.sc = sc;
	}


	public double getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(double loanAmt) {
		this.loanAmt = loanAmt;
	}
	

	public int getTenureMonths() {
		return tenureMonths;
	}

	public void setTenureMonths(int tenureMonths) {
		this.tenureMonths = tenureMonths;
	}

	@Override
	public void withdraw(double amt) {
		// TODO Auto-generated method stub
		
	}
	public void repayAmmount()
	{
		System.out.println("enter the choice \n1.regular EMI \n2.Custom Amount");
		int ch=sc.nextInt();
		if(ch==1){
			this.calculateEmi();
			this.deposit(this.getEmiAmt());
			System.out.println("you had loan of "+this.getLoanAmt());
			System.out.println("from which you have paid "+this.getEmiAmt());
			if(this.getBalance()>=0){
				System.out.println("You Loan is Settled");
			}
			else{
				System.out.println("remaining ammount to be paid is "+this.getBalance());
			}
		}
		else if(ch==2){
			System.out.println("Enter the Amount you want to repay");
			double amt=sc.nextDouble();
			
			this.deposit(amt);
			System.out.println("you had loan of "+this.getLoanAmt());
			System.out.println("from which you have paid "+amt);
			if(this.getBalance()>=0)
			{
				System.out.println("You Loan is Settled");
			}
			else
			{
				System.out.println("remaining ammount to be paid is "+this.getBalance());
				
			}
		
		}else {
			System.out.println("Invalid Choise");
		}
	}
	public double calculateEmi() {
	    double principal = this.getLoanAmt();
	    double interestRate = this.getIntrestRate() / 100 / 12; 
	    int tenureMonths = this.getTenureMonths();

	    double emi = (principal * interestRate * Math.pow(1 + interestRate, tenureMonths)) / (Math.pow(1 + interestRate, tenureMonths) - 1);
	    this.setEmiAmt(emi);

	    System.out.println("Loan Amount: " + this.getLoanAmt());
	    System.out.println("EMI: " + this.getEmiAmt());
	    System.out.println("Tenure Months: " + this.getTenureMonths());

	    return emi;
	}
	
	@Override
	public double calculateInterest() {
		double loanAmount = getBalance();  // Initial balance is the loan amount
        double rate = this.getIntrestRate();
        int t = 1;
        double simpleInterest = (loanAmount * rate * t) / 100;
        return simpleInterest;
		
	}
	public void display()
	{
		System.out.println("Account no : "+this.getAccno());
		System.out.println("Account Holde name : "+this.getHolderName());
		System.out.println("Acccount type : "+this.AccType);
		this.calculateEmi();
		System.out.println("Remaining Loan : "+this.getBalance());
		
		
	}
	
}
