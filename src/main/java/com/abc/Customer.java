package com.abc; 

 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.abc.AbstractAccount.AccountType;

 
public class Customer { 
     private String name; 
     private Map<AccountType,AbstractAccount> accounts; 
 
 
     public Customer(String name) { 
         this.name = name; 
         this.accounts = new HashMap<AccountType,AbstractAccount>(); 
     } 
 
     public String getName() { 
         return name; 
     } 
 
     public void openAccount(AccountType actType) { 
    	 if(accounts.containsKey(actType)) {
    		 throw new IllegalArgumentException("Account already exists"); 
    	 }
       	 switch(actType) {
       	 case CHECKING: 
       		 accounts.put(actType, new CheckingAccount());
       		 break;
       	 case SAVINGS:
       		accounts.put(actType, new SavingsAccount());
       		break;
       	 case MAXI_SAVINGS:
       		accounts.put(actType, new MaxiSavingAccount());
       		break;
       	 }
     } 
 
    public int getNumberOfAccounts() { 
         return accounts.size(); 
     } 
 
     public double totalInterestEarned() { 
         double total = 0; 
         for (AbstractAccount a : accounts.values()) 
             total += a.dailyIntRate(); 
         return total; 
     } 
 
    public boolean transfer(AccountType fromAct, AccountType toAct,double xferAmount) {
    	 double balance=accounts.get(fromAct).getBalance();
    	 if(balance < xferAmount){
    		 throw new IllegalArgumentException("insuffienct balance for transfer");
    	 }
    	 withdraw(xferAmount,fromAct);
    	 try {
    		 deposit(xferAmount,toAct);
    	 }	catch(Exception ex) {
			 throw new  IllegalArgumentException("transfer failed!")
    	 }  finally {
    		 deposit(xferAmount,fromAct);
    	 }
    	 return true;
    }
     
    public void deposit(double amount,AccountType acctType) { 
         if (amount <= 0) { 
             throw new IllegalArgumentException("amount must be greater than zero"); 
         } else { 
        	 accounts.get(acctType).getTransactions().add(new Transaction(amount)); 
         } 
     }
     
    public void withdraw(double withdrawAmount, AccountType acctType) { 
    	 if (withdrawAmount <= 0) { 
    		 throw new IllegalArgumentException("amount must be greater than zero"); 
    	 } else { 
    		 double balance = accounts.get(acctType).getBalance();
    		 if(balance < withdrawAmount ){
    			 throw new IllegalArgumentException("insufficient balance");
    		 }
    		 accounts.get(acctType).getTransactions().add(new Transaction(-1 * withdrawAmount)); 
    	 } 
     } 
     
     public double calculateTotalInterestEarned() {  
    	 double total = 0;  
    	 for (AbstractAccount a : accounts.values())  
    		 total += a.dailyIntRate();  
    	 return total;  
     }  
     
     public double totalBalance() {  
    	 double total = 0;  
    	 for (AbstractAccount a : accounts.values())  
    		 total += a.getBalance();  
    	 return total;  
     }  
     
     public List<AbstractAccount> getAccounts() {
    	 return (List<AbstractAccount>) accounts.values();
     }
     
     public Map<AccountType,AbstractAccount> getAccountMap() {
    	 return accounts;
     }

 } 
