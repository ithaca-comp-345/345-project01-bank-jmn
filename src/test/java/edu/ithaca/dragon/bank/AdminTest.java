package edu.ithaca.dragon.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



public class AdminTest {
    
    @Test
    void freezeUnFreezeAccountTest(){
        //Just freezes the account Number
        CentralBank bank = new CentralBank();
        BankTeller teller = new BankTeller(bank);
        teller.addUserAccount("2222", 100);
        Admin newAdmin = new Admin(bank.getUserAccount(100).getAccountNumber());
        assertEquals(false, newAdmin.getFreezeAccount());
        newAdmin.freezeOrUnFreezeAccount(true);
        assertEquals(true, newAdmin.getFreezeAccount());
    }


}
