package com.abc;

import org.junit.Test;

import com.abc.AbstractAccount.AccountType;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void testCustomerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(AccountType.CHECKING);
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", ReportGenerator.generateCustomerSummary(bank));
    }

    @Test
    public void testCheckingAccount() {
        Bank bank = new Bank();
        Customer bill = new Customer("Bill");
        bill.openAccount(AccountType.CHECKING);
        bank.addCustomer(bill);
        bill.deposit(1000.0,AccountType.CHECKING);
        System.out.println(bank.totalInterestPaid());
        assertEquals(0.002739726027357392, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testSavingsAccount() {
    	 Bank bank = new Bank();
         Customer bill = new Customer("Bill");
         bill.openAccount(AccountType.SAVINGS);
         bank.addCustomer(bill);
         bill.deposit(1500.0,AccountType.SAVINGS);
         System.out.println(bank.totalInterestPaid());
         assertEquals(0.6904109589040672, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testMaxiSavingsAccount() {
    	Bank bank = new Bank();
        Customer bill = new Customer("Bill");
        bill.openAccount(AccountType.MAXI_SAVINGS);
        bank.addCustomer(bill);
        bill.deposit(3000.0,AccountType.MAXI_SAVINGS);
        System.out.println(bank.totalInterestPaid());
        assertEquals(4.109589041096115, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
