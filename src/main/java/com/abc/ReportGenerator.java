package com.abc;  

import java.util.List;  
import static java.lang.Math.abs;  


public class ReportGenerator {  
	
    public static String generateCustomerSummary(Bank bank) {  
        List<Customer> customers = bank.getCustomers();  
        String summary = "Customer Summary";  
        for (Customer c : customers)  
           summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";  
        return summary;  
    }  
 
    private static String format(int number, String word) {  
         return number + " " + (number == 1 ? word : word + "s");  
    }  
 
    public static String generateCustomerStatement(Customer customer) {  
         String name = customer.getName();  
         List<AbstractAccount> accounts = customer.getAccounts();  
         String statement = "Statement for " + name + "\n";  
         double total = 0.0;  
         for (AbstractAccount a : accounts) {  
             statement += "\n" + generateAccountStatement(a) + "\n";  
             total += a.getBalance();  
         }  
         statement += "\nTotal In All Accounts " + toDollars(total);  
         return statement;  
     }  
 
    
    public static String generateAccountStatement(AbstractAccount a) {  
    	String s = "";  
     //Translate to pretty account type  
        switch(a.getAccountType()){  
            case CHECKING:  
                 s += "Checking Account\n";  
                 break;  
             case SAVINGS:  
                 s += "Savings Account\n";  
                 break;  
             case MAXI_SAVINGS:  
                 s += "Maxi Savings Account\n";  
                 break;  
         }  
         //Now total up all the transactions  
         double total = 0.0;  
         for (Transaction t : a.getTransactions()) {  
             s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";  
             total += t.amount;  
         }  
         s += "Total " + toDollars(total);  
         return s;  
    }  
     
    
    private static String toDollars(double d){  
       return String.format("$%,.2f", abs(d));  
     }  
 }  
