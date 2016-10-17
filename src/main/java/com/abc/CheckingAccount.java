package com.abc;

public class CheckingAccount extends AbstractAccount{
	
	private final double intRate = 0.001;
	private final int accuralFrequency = 1;
	
	public CheckingAccount() { 
		super(AccountType.CHECKING);
    } 
	
	public double dailyIntRate() { 
	    double balance = getBalance(); 
        return calculateDailyIntRate(balance,intRate,accuralFrequency);
     } 
}
