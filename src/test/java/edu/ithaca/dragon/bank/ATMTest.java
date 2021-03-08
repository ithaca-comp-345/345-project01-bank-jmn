package edu.ithaca.dragon.bank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {
    @Test
    void getBalanceTest() {
        //Get Balance Test
        //Integration Test
        CentralBank bank = new CentralBank();
        BankTeller teller = new BankTeller(bank);
        ATM atm = new ATM(bank);
        teller.addUserAccount("2222", 10);
        teller.addCheckingAccount();
        teller.addSavingsAccount();
        atm.confirmCredentials(10, "2222");
        assertEquals(0, atm.getBalance("checking"));
        assertEquals(0, atm.getBalance("savings"));
        
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        //Withdraw test 
        //Integration test
        CentralBank bank = new CentralBank();
        BankTeller teller = new BankTeller(bank);
        ATM atm = new ATM(bank);
        teller.addUserAccount("2222", 10);
        teller.confirmCredentials(10, "2222");
        teller.addCheckingAccount();
        teller.addSavingsAccount();
        atm.confirmCredentials(10, "2222");
        atm.deposit(200, "savings");
        assertEquals(200, atm.getBalance("savings"));
        atm.withdraw(100, "savings");
        assertEquals(100, atm.getBalance("savings"));

    }

    @Test
    void depositTest(){
        //Deposit Test
        //Integration
        CentralBank bank = new CentralBank();
        BankTeller teller = new BankTeller(bank);
        ATM atm = new ATM(bank);
        teller.addUserAccount("2222", 10);
        teller.confirmCredentials(10, "2222");
        teller.addCheckingAccount();
        teller.addSavingsAccount();
        atm.confirmCredentials(10, "2222");
        atm.deposit(200, "savings");
        assertEquals(200, atm.getBalance("savings"));
        atm.deposit(200, "savings");
        assertEquals(400, atm.getBalance("savings"));
        atm.deposit(200, "checking");
        assertEquals(200, atm.getBalance("checking"));
    }

    @Test
    void confirmCredentialsTest(){
        //Confirm credentials 
        CentralBank bank = new CentralBank();
        BankTeller teller = new BankTeller(bank);
        ATM atm = new ATM(bank);
        teller.addUserAccount("2222", 10);
        assertEquals(true, atm.confirmCredentials(10, "2222"));
        assertEquals(false, atm.confirmCredentials(10, "2022"));
        
    }

    @Test
    void transferTest() throws InsufficientFundsException {
        //Transfer 
        CentralBank bank = new CentralBank();
        BankTeller teller = new BankTeller(bank);
        ATM atm = new ATM(bank);
        teller.addUserAccount("1111", 11);
        teller.confirmCredentials(11, "1111");
        teller.addCheckingAccount();
        teller.addSavingsAccount();
        teller.close();
        atm.confirmCredentials(11, "1111");
        atm.deposit(200, "savings");
        atm.transfer(11, "savings", 100);
        assertEquals(100, atm.getBalance("savings"));
        assertEquals(100, atm.getBalance("checking"));
    }
    
}
