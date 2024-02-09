package com.ranjit.bankSystem;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		try (Scanner sc = new Scanner (System.in)) {
			Bank bk=new Bank();
			int ch;
			do {
				System.out.println("\t\t1. Create Account");
				System.out.println("\t\t2. Login");
				System.out.println("\t\t3. Admin Level Report");
				System.out.println("\t\t4. Exit");
				System.out.print("\t\tEnter choice: ");
				ch=sc.nextInt();
				switch(ch) {
					case 1:{
						System.out.println("\t\t1.Saving Account\n\t\t2.Current Account\n\t\t3.Salary Account\n\t\t4.Loan Account");
						System.out.print("Enter Choice: ");
						int a=sc.nextInt();
						bk.addAccount(a);
						break;
					}
					case 2:
					{
						System.out.println("enter your account number");
						long acn=sc.nextLong();
						bk.login(acn);
						break;
					}
					case 3:{
						bk.adminReport();
						break;
					}
					case 4:{
						ch=10;
					}
				}	
			}while(ch!=10);
		}
	}
}