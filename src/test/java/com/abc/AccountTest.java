package com.abc;  
import org.junit.Test;

import com.abc.AbstractAccount.AccountType;

import static org.junit.Assert.assertEquals;  
public class AccountTest {  
   
private static final double DOUBLE_DELTA = 1e-15;  
   	
	@Test  
    public void testAccountBalances() {  
        Customer bill = new Customer("Bill");  
        bill.openAccount(AccountType.CHECKING);  
        bill.deposit(100.0, AccountType.CHECKING);  
        assertEquals(100.0, bill.getAccountMap().get(AccountType.CHECKING).getBalance(), DOUBLE_DELTA);  
   
        bill.openAccount(AccountType.SAVINGS);  
        bill.deposit(100.0, AccountType.SAVINGS);  
        assertEquals(100.0, bill.getAccountMap().get(AccountType.SAVINGS).getBalance(), DOUBLE_DELTA);  
  
        bill.openAccount(AccountType.MAXI_SAVINGS);  
        bill.deposit(100.0, AccountType.MAXI_SAVINGS);  
        assertEquals(100.0, bill.getAccountMap().get(AccountType.MAXI_SAVINGS).getBalance(), DOUBLE_DELTA);  
    }  
   
    @Test  
    public void testAccountInterests() {  
         Customer bill = new Customer("Bill");  
         bill.openAccount(AccountType.CHECKING);  
         bill.deposit(10000.0, AccountType.CHECKING);  
         assertEquals(0.02739726027357392, bill.getAccountMap().get(AccountType.CHECKING).dailyIntRate(), DOUBLE_DELTA); 
      
         bill.openAccount(AccountType.SAVINGS);  
         bill.deposit(10000.0, AccountType.SAVINGS);  
         System.out.println( bill.getAccountMap().get(AccountType.SAVINGS).dailyIntRate());
         assertEquals(12.334246575343059, bill.getAccountMap().get(AccountType.SAVINGS).dailyIntRate(), DOUBLE_DELTA); 
      
         bill.openAccount(AccountType.MAXI_SAVINGS);  
         bill.deposit(10000.0, AccountType.MAXI_SAVINGS);
         System.out.println( bill.getAccountMap().get(AccountType.MAXI_SAVINGS).dailyIntRate());
         assertEquals(13.698630136987049, bill.getAccountMap().get(AccountType.MAXI_SAVINGS).dailyIntRate(), DOUBLE_DELTA); 
     }  
  
   
    @Test  
    public void testAccountStatement() {  
        Customer bill = new Customer("Bill");  
        bill.openAccount(AccountType.CHECKING);  
        bill.openAccount(AccountType.SAVINGS);  
        bill.openAccount(AccountType.MAXI_SAVINGS);  
        bill.deposit(1000.0, AccountType.CHECKING);  
        bill.withdraw(500.0, AccountType.CHECKING);  
        bill.deposit(2500.0, AccountType.SAVINGS);  
        bill.withdraw(1000.0, AccountType.SAVINGS);  
        bill.deposit(4000.0, AccountType.MAXI_SAVINGS);  
        bill.withdraw(100.0, AccountType.MAXI_SAVINGS);  
        AbstractAccount checkingAccount = bill.getAccountMap().get(AccountType.CHECKING);  
        AbstractAccount savingsAccount = bill.getAccountMap().get(AccountType.SAVINGS);  
        AbstractAccount maxiSavingsAccount = bill.getAccountMap().get(AccountType.MAXI_SAVINGS);  
        assertEquals("Checking Account\n" +  
              "  deposit $1,000.00\n" +  
                 "  withdrawal $500.00\n" +  
                 "Total $500.00", ReportGenerator.generateAccountStatement(checkingAccount));  
         assertEquals("Savings Account\n" +  
                 "  deposit $2,500.00\n" +  
                 "  withdrawal $1,000.00\n" +  
                 "Total $1,500.00", ReportGenerator.generateAccountStatement(savingsAccount));  
         assertEquals("Maxi Savings Account\n" +  
                "  deposit $4,000.00\n" +  
                 "  withdrawal $100.00\n" +  
                "Total $3,900.00", ReportGenerator.generateAccountStatement(maxiSavingsAccount));  
     }  
}  
