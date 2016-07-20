package com.abc;

import java.util.Date;

public class CheckingAccount extends Account {
	
	

	 public CheckingAccount(int accountType) {
		super(accountType);
		
	}

	public double interestEarned(double amount) {
	        return (amount * Math.pow((1 + (0.001 / 365)), 365)) - amount;
	    }




 }

