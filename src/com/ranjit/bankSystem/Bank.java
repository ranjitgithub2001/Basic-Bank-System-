package com.ranjit.bankSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
	
	List<Account> acc=new ArrayList<>();
//	int AccCount;
	int closeCount;
	int openCount;
	
	Scanner sc=new Scanner(System.in);
	

	public Bank() {
		super();
		
//		this.AccCount=-1;
		this.closeCount=0;
		this.openCount=0;
	}
	public void addAccount(int a) {
		System.out.println("Enter the Account number:");
		long num=sc.nextLong();
		System.out.println("Enter the Account Holder Name:");
		sc.nextLine();
		String hName=sc.nextLine();
		
		if(a==1) {
			System.out.println("Enter the Initial Balance:");
			double bal=sc.nextDouble();
			if(bal>=10000)
			{
			SavingAcc s=new SavingAcc(num,hName,"Saving",bal);
			acc.add(s);
//			this.acc[++AccCount]=s;
			this.openCount++;
			}
			else
			{
				System.out.println("Minumum Balance to create Account is 10000");
			}

		}else if(a==2) {
			System.out.println("Enter the Initial Balance:");
			double bal=sc.nextDouble();
			CurrentAcc c=new CurrentAcc(num,hName,"Current",bal);
			acc.add(c);
//			this.acc[++AccCount]=c;
			this.openCount++;

		}else if(a==3) {
			System.out.println("Enter the Initial Balance:");
			double bal=sc.nextDouble();
			System.out.println("Enter the name of Employer:");
			sc.nextLine();
			String nm=sc.nextLine();
			LocalDate date=LocalDate.now();
			SalaryAcc sa=new SalaryAcc(num,hName,"Salary",bal,date,nm);
			acc.add(sa);
//			this.acc[++AccCount]=sa;
			this.openCount++;

		}else if(a==4) {
			System.out.println("Enter the Loan Ammount:");
			double bal=sc.nextDouble();
			System.out.println("enter the tenure period (in months) of the loan amount");
			int tm=sc.nextInt();
			LoanAcc l=new LoanAcc(num,hName,"Loan",bal,tm);
			acc.add(l);
//			this.acc[++AccCount]=l;
			this.openCount++;

		}else {
			System.out.println("Invalid Choice!!!!");
		}
	}
	public void closeAccount(int i) 
	{
	      
	        	acc.get(i).setAccStatus("Close");
	            this.closeCount++;
	            System.out.println("Account with AccNo " + acc.get(i).getAccno() + " has been closed.");
	            
	 }
	 
	public void accLifeCycle() {
		System.out.println("Accounts Open Till Now: "+ this.openCount);
		System.out.println("Accounts Closed Till Now: "+ this.closeCount);
	}
	public int searchAccount(long Acno)
	{
		for(int i=0;i<=acc.size();i++)
		{
			if(Acno==acc.get(i).getAccno())
			{
				return i;
				
			}
		}
		return -1;
	}
	public void overCounterActi(int j) 
	{
		if(j!=-1)
		{
			System.out.println("Account number: "+acc.get(j).getAccno());
			System.out.println("Account Holder Name: "+acc.get(j).getAccno());
			System.out.println("Opening Balance: "+acc.get(j).tempOpening);
			txDisplay(j);
			System.out.println("Closing Balace: "+ ClosingBal(j));
		}
		else
		{
			System.out.println("Account not found");
		}
		
			
	}
	public void txDisplay(int j)
	{
		System.out.println("Amount        Transcation Type");
		for(int k=0;k<=acc.get(j).getTxCount();k++)
		{
			System.out.println(acc.get(j).txInfo.get(k).getAmount()+"       "+acc.get(j).txInfo.get(k).getTxType());
		}
	}
	public double ClosingBal(int j)
	{
		double crTotal = 0;
		double dbTotal=0;
		for(int k=0;k<=acc.get(j).getTxCount();k++)
		{
			if(acc.get(j).txInfo.get(k).getTxType()=="Credit")
			{
				crTotal+=acc.get(j).txInfo.get(k).getAmount();
				
			}
			else
			{
				dbTotal+=acc.get(j).txInfo.get(k).getAmount();
			}
		}
		double closingBalance=(acc.get(j).tempOpening+crTotal)-dbTotal;
		return closingBalance;	
	}
	public void login(long accno)
	{
		int i=this.searchAccount(accno);
		if(this.checkStatus(i))
		{
		if(i!=-1) {
			System.out.println("Enter the Operation you want to perform \n\t\t1.Deposite \n\t\t2.Withdraw\n\t\t3.Account Status\n\t\t4.Calculate Interest\n\t\t5.Get Account Details\n\t\t6.Close Account");
			int c=sc.nextInt();
			if(c==1)
			{
				if(acc.get(i) instanceof LoanAcc) {
					LoanAcc l=(LoanAcc) acc.get(i);
					l.repayAmmount();
					
				}else {
					System.out.println("enter the ammount you want to deposite");
					double amt=sc.nextDouble();
					if(acc.get(i) instanceof SalaryAcc) {
						SalaryAcc s=(SalaryAcc) acc.get(i);
						s.deposit1(amt);
						
					}else{
						acc.get(i).deposit(amt);
					}
				}
			}
			else if(c==2)
			{
				System.out.println("enter the ammount you want to Withdraw");
				double amt=sc.nextDouble();
				acc.get(i).withdraw(amt);
			}else if(c==4) {
				System.out.println("Account Number is: "+acc.get(i).getAccno());
				System.out.println("Account Holder Name is: "+acc.get(i).getHolderName());
				System.out.println("Intrest you will be getting: "+acc.get(i).calculateInterest());
			}else if(c==5) {
				acc.get(i).display();
			}else if(c==6)
			{
				this.closeAccount(i);
			}else if(c==3) {
				this.overCounterActi(i);
			}else
			{
				System.out.println("Invalid Choice!!");
			}
		}else {
			System.out.println("Account is not found!!!!");
		}
		}
		else
		{
			System.out.println("Your account is Closed");
		}
		
	}
	public void showBalance() {
		System.out.println("enter your account number");
		long acn=sc.nextLong();
		int i=this.searchAccount(acn);
		System.out.println(acc.get(i).getBalance());
	}
	
	public void adminReport() {
		System.out.println("Are you an Admin y=1\n=0");
		int n=sc.nextInt();
		int ch = 0;
		if(n==1) {
			do {
				System.out.println("Enter the Password");
				sc.nextLine();
				String s=sc.next();
				if(s.equals("******")) {
					System.out.println("\t\t1.Account Life Cycle\n\t\t2.Get An End of the Day Report\n\t\t3.Bank Balance Sheet");
					int x=sc.nextInt();
					if(x==1) {
						this.accLifeCycle();
					}else if(x==2) {
						this.EndOfTheDayReport();
					}else if(x==3) {
						this.bankBalanceSheet();
					}else {
						System.out.println("Invalid Choice!!!");
					}
				}else {
					System.out.println("Invalid Password try again");
					ch=0;
				}
			}while(ch!=0);
			
		}
	}
//	public void getAccDetails()
//	{
//		System.out.println("Enter the Account number");
//		long acn=sc.nextLong();
//		int i=this.searchAccount(acn);
//		this.acc[i].display();
//	}
	public void EndOfTheDayReport()
	{
		this.accLifeCycle();
		for(int i=0;i<=acc.size();i++)
		{
			System.out.println("Account number : "+acc.get(i).getAccno());
			System.out.println("Account Holder Name : "+acc.get(i).getHolderName());
			System.out.println("Opening balance  : "+acc.get(i).tempOpening);
			
			if(acc.get(i) instanceof SalaryAcc){	
				SalaryAcc s = (SalaryAcc)acc.get(i);
				if(s.equals("Freezed")) {
					System.out.println("Account Status : Freezed");
				}else {
					System.out.println("Account Status : Active");
				}
			}else if(acc.get(i) instanceof LoanAcc) {
				LoanAcc l=(LoanAcc) acc.get(i);
				System.out.println("Loan Amount : "+l.getLoanAmt());
				System.out.println("Regular EMI : "+l.calculateEmi());
			}
			for(int k=0;k<=acc.get(i).getTxCount();k++)
			{
				System.out.println(acc.get(i).txInfo.get(k).amount+" "+ acc.get(i).txInfo.get(k).txType);
			}
			System.out.println("Closing Balance : "+this.ClosingBal(i));
		}
	}
	
	public void calIneterest() {
		System.out.println("Enter the Account number");
		long acn=sc.nextLong();
		int n=this.searchAccount(acn);
		System.out.println("You'll be charged of "+ acc.get(n).calculateInterest()+" Rupees at the End of the Day");
	}
	public int getCloseCount() {
		return closeCount;
	}
	public void setCloseCount(int closeCount) {
		this.closeCount = closeCount;
	}
	public int getOpenCount() {
		return openCount;
	}
	public void setOpenCount(int openCount) {
		this.openCount = openCount;
	}
	public boolean checkStatus(int i)
	{
		if(acc.get(i).AccStatus.equals("Active"))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	public void bankBalanceSheet() {
		double closingAmt = 0;
		double openingBalance=0;
		int crCount=0,dbCount=0;
		for(int i=0;i<=acc.size();i++) {
			if(acc.get(i).AccType.equals("Credit")) {
				crCount++;
			}else {
				dbCount++;
			}
			openingBalance= openingBalance + acc.get(i).tempOpening;
			closingAmt = closingAmt + this.ClosingBal(i);
		}
		System.out.println("Opening Balance of Bank: "+openingBalance);
		System.out.println("Number of Credit Type Transactions: "+crCount);
		System.out.println("Number of Debit Type Transactions: "+dbCount);
		System.out.println("Closing Balance of Bank: "+closingAmt);
		
		
	}
}
