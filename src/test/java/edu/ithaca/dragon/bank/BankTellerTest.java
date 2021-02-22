package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTellerTest {

    @Test
    void addUserAccountTest() throws InsufficientFundsException{
        CentralBank bank = new CentralBank();
        BankTeller teller = new BankTeller(bank);
        teller.addUserAccount("2222", 10);
        assertEquals(10, bank.getUserAccount(10).getAccountNumber());
        assertEquals("2222", bank.getUserAccount(10).getPassword());
    
    }


    @Test
    void addCheckingTest(){
        CentralBank bank = new CentralBank();
        BankTeller teller = new BankTeller(bank);
        teller.addUserAccount("2222", 10);
        teller.confirmCredentials(10, "2222");
        teller.addCheckingAccount();
        assertNotEquals(null, bank.getUserAccount(10).getCheckingAccount());
        
    }

    @Test
    void addSavingsTest(){
        CentralBank bank = new CentralBank();
        BankTeller teller = new BankTeller(bank);
        teller.addUserAccount("2222", 10);
        teller.confirmCredentials(10, "2222");
        teller.addSavingsAccount();
        assertNotEquals(null, bank.getUserAccount(10).getSavingsAccount());
    }

    
    
    
}
