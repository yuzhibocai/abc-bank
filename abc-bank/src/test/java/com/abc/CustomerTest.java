package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new CheckingAccount(Account.CHECKING);
        Account savingsAccount = new SavingAccount(Account.SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

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
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new SavingAccount(Account.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingAccount(Account.SAVINGS));
        oscar.openAccount(new CheckingAccount(Account.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingAccount(Account.SAVINGS));
        oscar.openAccount(new CheckingAccount(Account.CHECKING));
        oscar.openAccount(new MaxiSavingAccount(Account.MAXI_SAVINGS));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test
    public void testDeposit(){
    	Customer oscar = new Customer("Oscar")
                .openAccount(new SavingAccount(Account.SAVINGS));
    	oscar.getAccount(0).deposit(1500);
    	assertEquals((int)1500, (int)oscar.getAccount(0).getCurrentBalance());
    }
    
    @Test
    public void testWithdraw(){
    	Customer oscar = new Customer("Oscar")
                .openAccount(new SavingAccount(Account.SAVINGS));
    	oscar.getAccount(0).deposit(1500);
    	oscar.getAccount(0).withdraw(900);
    	assertEquals((int)600, (int)oscar.getAccount(0).getCurrentBalance());
    }
    
    @Test
    public void testTransfer(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingAccount(Account.SAVINGS));
        oscar.openAccount(new CheckingAccount(Account.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
        
        oscar.getAccount(0).deposit(1000);

        try {
        	oscar.getAccount(0).transfer(oscar, 0, 1, 100);
        } catch (Exception e ) {
        	e.printStackTrace();
        }
        
        assertEquals((int)100, (int) oscar.getAccount(1).getCurrentBalance());
        assertEquals((int)900, (int) oscar.getAccount(0).getCurrentBalance());
    }
}
