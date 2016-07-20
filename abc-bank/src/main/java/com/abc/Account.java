package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public abstract class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;
  
    private double currentBalance = 0;  
 
    private final int accountType;
    public List<Transaction> transactions;

    public abstract double InterestEarned();

    public abstract double InterestEarned(Date asOfDate);
    
    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }
    
    public double getCurrentBalance() {
    	return currentBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
        	currentBalance += amount;
        	Transaction t = new Transaction(amount);
        	
            transactions.add(t);
            
        }
    }


public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
    	currentBalance -= amount;
    	Transaction t = new Transaction(-amount);
    	
        transactions.add(t);
      
    }
}



public synchronized void transfer(Customer customer, int fromAcct, int toAcct, double amount) throws Exception {
	if (amount <= 0){
        throw new IllegalArgumentException("transfer amount must be greater than zero");
	}
	
	Account sourceAcct = customer.getAccount(fromAcct);
	Account targetAcct = customer.getAccount(toAcct);
	if(sourceAcct != null && targetAcct != null) {
		if (amount < sourceAcct.getCurrentBalance()){
			sourceAcct.withdraw(amount);
		}
			
		else
			throw new Exception("Insufficient funds to transfer ");
	}
	targetAcct.deposit(amount);
}


//    public double interestEarned() {
//        double amount = sumTransactions();
//        
//        
//        switch(accountType){
//            case CHECKING:
//            	return amount * 0.001;
//            case SAVINGS:
//                if (amount <= 1000)
//                    return amount * 0.001;
//                else
//                    return 1 + (amount-1000) * 0.002;
//
//            case MAXI_SAVINGS:
//            	return getMaxiSavingsAcctInt();
//            	 
//            default:
//            	throw new IllegalArgumentException("Invalid account type");
//        }
//    }


	public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
       
        for (Transaction t: transactions)
        	amount += t.amount;
        	
        
            
        
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

}
