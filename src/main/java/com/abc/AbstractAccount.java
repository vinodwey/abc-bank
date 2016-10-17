package com.abc;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractAccount {
	
	 public enum AccountType { CHECKING, SAVINGS, MAXI_SAVINGS }; 
	
	 public AccountType accountType; 
     public List<Transaction> transactions; 
          
     public List<Transaction> getTransactions() {
    	 return transactions;
     }
     
     public abstract double dailyIntRate();
     
     public AbstractAccount(AccountType accountType) {  
    	   this.accountType = accountType;  
    	   this.transactions = new ArrayList<Transaction>();  
     }  

    	 
     public double getBalance() { 
          double balance = 0.0; 
          for (Transaction t: transactions) 
              balance += t.amount; 
          return balance; 
      } 
      
   
      public static double calculateDailyIntRate(double amount, double annualRate, int days) {
    	  if( amount < 0 ) return 0d;
          return ( amount * Math.pow(1.0 + annualRate/365, days)) - amount;
      }
      
      public int daysSinceTransaction(Transaction transaction) {
     	 return (int) ((DateProvider.getInstance().now().getTime() - transaction.getTransactionDate().getTime()) / (1000 * 60 * 60 * 24));
      }
      
      public AccountType getAccountType() {
    	  return accountType;
      }
  
}
