package com.abc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.abc.AbstractAccount.AccountType;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
	private static final double DOUBLE_DELTA = 1e-15;  

	public void testCustStatement(){
    	
    	Customer henry = new Customer("Henry");
    	henry.openAccount(AccountType.CHECKING);
    	henry.openAccount(AccountType.SAVINGS);

        henry.deposit(100.0,AccountType.CHECKING);
        henry.deposit(4000.0,AccountType.SAVINGS);
        henry.withdraw(200.0,AccountType.SAVINGS);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", ReportGenerator.generateCustomerStatement(henry));
    }

    @Test
    public void testOneAccount(){
    	Customer henry = new Customer("Henry");
    	henry.openAccount(AccountType.CHECKING);
    	assertEquals(1, henry.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
    	Customer henry = new Customer("Henry");
    	henry.openAccount(AccountType.CHECKING);
    	henry.openAccount(AccountType.SAVINGS);
    	assertEquals(2, henry.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
    	Customer henry = new Customer("Henry");
    	henry.openAccount(AccountType.CHECKING);
    	henry.openAccount(AccountType.SAVINGS);
    	henry.openAccount(AccountType.MAXI_SAVINGS);
    	assertEquals(3, henry.getNumberOfAccounts());
    }
    
    public void testTransfer() {  
    	Customer henry = new Customer("Henry");
    	henry.openAccount(AccountType.CHECKING);
    	henry.openAccount(AccountType.SAVINGS);
        henry.deposit(350.0, AccountType.CHECKING);  	

        henry.deposit(800, AccountType.SAVINGS);  
        henry.transfer(AccountType.CHECKING, AccountType.SAVINGS,250);  
        assertEquals(344,henry.totalBalance(),DOUBLE_DELTA);
    }
    
    
    @Test  
    public void testInterestEarned() {  
    	Customer henry = new Customer("Henry");  
    	henry.openAccount(AccountType.CHECKING);  
    	henry.openAccount(AccountType.SAVINGS);  
    	henry.deposit(1000.0, AccountType.CHECKING);  
        henry.deposit(2000.0, AccountType.SAVINGS);  
        assertEquals(1.378082191780777, henry.calculateTotalInterestEarned(), DOUBLE_DELTA);  
   }  
   
    
    @Rule  
    public ExpectedException thrown = ExpectedException.none();  
    @Test  
    public void testFailedWithdraw() {  
    	Customer henry = new Customer("Henry");  
    	henry.openAccount(AccountType.CHECKING);  
      	thrown.expect(IllegalArgumentException.class);  
    	thrown.expectMessage("insufficient balance");  
    	henry.withdraw(200.0, AccountType.CHECKING);  
 }  
   
   @Test  
   public void testFailedTransfer() {  
	   Customer henry = new Customer("Henry");  
   	   henry.openAccount(AccountType.CHECKING); 
   	   henry.deposit(1000.0, AccountType.CHECKING);  
   	   henry.openAccount(AccountType.SAVINGS);  
   	   henry.deposit(2000.0, AccountType.SAVINGS);  
       thrown.expect(IllegalArgumentException.class);  
       thrown.expectMessage("insuffienct balance for transfer");  
       henry.transfer(AccountType.CHECKING, AccountType.SAVINGS,4000.0);  
 }  
 
     
}
