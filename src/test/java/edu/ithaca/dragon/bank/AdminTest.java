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
        Admin newAdmin = new Admin(bank);
        teller.confirmCredentials(100, "2222");
        teller.addSavingsAccount();
        teller.addCheckingAccount();
        newAdmin.freezeAccount(100);
        //assertThrows(FrozenAccountException.class, ()-> teller.deposit(100.0, "savings"));
        teller.deposit(100.0, "savings");
        assertEquals(0.0, teller.getBalance("savings"));
        newAdmin.unfreezeAccount(100);
        teller.deposit(100.0, "savings");
        assertEquals(100.0, teller.getBalance("savings"));


        
        
    }


}
