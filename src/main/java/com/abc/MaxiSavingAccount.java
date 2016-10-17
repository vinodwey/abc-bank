package com.abc; 

 
import java.util.ListIterator;

public class MaxiSavingAccount extends AbstractAccount { 
	
	public MaxiSavingAccount() { 
        super(AccountType.MAXI_SAVINGS);
     } 
 
    public double dailyIntRate() { 
         double balance = getBalance(); 
         if(! recentWithdrawal(10)) {
        	 return calculateDailyIntRate(balance, 0.5,1);
         } else {
        	 return calculateDailyIntRate(balance,0.001,1);
         }
     } 

     private boolean recentWithdrawal(int thresholdDays){
    	 ListIterator<Transaction> li = this.getTransactions().listIterator(this.getTransactions().size());
    	 while(li.hasPrevious()) {
    		 Transaction t = (Transaction) li.previous();
    		 if (t.amount < 0.0) {
    			 if(daysSinceTransaction(t) <= thresholdDays) {
    				 return true;
    			 }
    		 }
    	 }
    	 return false;
    	 
     }

	
 } 

