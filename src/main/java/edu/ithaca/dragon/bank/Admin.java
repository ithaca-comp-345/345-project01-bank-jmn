package edu.ithaca.dragon.bank;

public class Admin {
    private CentralBank bank;

    public Admin(CentralBank bank){
        this.bank = bank;
    }

    public void freezeOrUnfreeze(int id){
        bank.freezeOrUnFreeze(id);
    }

    
}
