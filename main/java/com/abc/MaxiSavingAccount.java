package com.abc;

import java.util.Date;

public class MaxiSavingAccount extends Account {


	private int accountType;

	public MaxiSavingAccount(int accountType) {
		super(accountType);
		this.accountType=Account.MAXI_SAVINGS;
	}

	

	public double InterestEarned() {
		return this.InterestEarned(null);
	}

	public double InterestEarned(Date asOfDate)
        {
            double amount = 0;
            double interest = 0;
            double numberOfdays = 0;
            double rate = 0;
            boolean hasWithdrawalsInPast10Days = false;

            for (Transaction transaction : this.transactions)
            {
                if (asOfDate.toString()!=null && !hasWithdrawalsInPast10Days)
                    hasWithdrawalsInPast10Days = ((transaction.amount < 0) && (asOfDate.getTime() - transaction.getDate().getTime()) <= 10);

                if (asOfDate.toString()==null)
                    numberOfdays += Math.abs((int) asOfDate.getTime() - (int) transaction.getDate().getTime());

                amount += (transaction.amount);
            }

            if (asOfDate.toString()!=null)
                numberOfdays = 365;

            // rate of 2% for first $1,000 then 5% for next $1,000 then 10%
            if (amount <= 1000)
            {
                rate = 0.02;
                interest = (amount * rate * (numberOfdays / 365));
            }
            if (amount <= 2000)
            {
                if (hasWithdrawalsInPast10Days)
                    rate = 0.01;
                else
                    rate = 0.05;
                interest = ((1000 * 0.02) + (amount - 1000) * rate) * (numberOfdays / 365);
            }
            rate = 0.1;
            if (hasWithdrawalsInPast10Days)
                interest = ((1000 * 0.02) + (1000 * 0.01) + (amount - 2000) * rate) * (numberOfdays / 365);
            else
                interest = ((1000 * 0.02) + (1000 * 0.05) + (amount - 2000) * rate) * (numberOfdays / 365);

            return interest;
        }
}
