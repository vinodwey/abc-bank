package com.abc; 


public class SavingsAccount extends AbstractAccount { 
  
     public SavingsAccount() { 
         super(AccountType.SAVINGS);
     } 
     
     public double dailyIntRate() { 
         double balance = getBalance(); 
         if (balance <= 1000) 
        	  return calculateDailyIntRate(balance,0.002,1); 
         else {
        	  double first = calculateDailyIntRate(1000,0.002,1);
         	  double secondRest = calculateDailyIntRate(balance-1000,0.5,1);
           	  return  first + secondRest; 
         }
                       
     } 

  
 } 

