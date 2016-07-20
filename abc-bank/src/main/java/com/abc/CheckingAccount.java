package com.abc;

import java.util.Date;

public class CheckingAccount extends Account {
	public CheckingAccount(int accountType) {
		super(accountType);
		this.accountType=Account.CHECKING;
	}
	private int accountType;
    
	public  double InterestEarned()
     {
         return this.InterestEarned(null);
     }


     public  double InterestEarned(Date asOfDate)
     {
         double amount = 0;
         double numberOfdays = 0;
         double interest = 0;

         for (Transaction transaction : this.transactions)
         {
             if (asOfDate.toString()!=null)
                 numberOfdays += Math.abs((int)asOfDate.getTime() - (int)transaction.getDate().getTime());
             amount += transaction.amount;
         }

         if (asOfDate.toString()==null)
             numberOfdays = 365;

         interest = (amount * 0.001) * (numberOfdays / 365);
         return interest;
     }
 }

