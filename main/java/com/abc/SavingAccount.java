package com.abc;

import java.util.Date;

public class SavingAccount extends Account{
	public SavingAccount(int accountType) {
		super(accountType);
		this.accountType=Account.SAVINGS;
	}

	private int accountType;


     public  double InterestEarned()
     {
         return this.InterestEarned(null);
     }

     public  double InterestEarned(Date asOfDate)
     {
         double amount = 0;
         double interest = 0;
         double numberOfdays = 0;
         for (Transaction transaction : this.transactions)
         {
             if (asOfDate.toString()!=null)
                 numberOfdays += Math.abs((int) asOfDate.getTime() - (int) transaction.getDate().getTime());
             amount += transaction.amount;
         }

         if (asOfDate.toString()==null)
             numberOfdays = 365;


         // rate of 0.1% for first $1,000 then 0.2%
         if (amount <= 1000)
             interest = (amount * 0.001) * (numberOfdays / 365);
         else
             interest = (1 + (amount - 1000) * 0.002) * (numberOfdays / 365);
         return interest;
     }
 
}
