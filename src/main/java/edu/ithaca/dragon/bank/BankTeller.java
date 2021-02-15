package edu.ithaca.dragon.bank;

public class BankTeller extends ATM{

    private CentralBank centralBank;
    private UserAccount currentUserAcc;

    public BankTeller(CentralBank bank){
        super(bank);
    }

    public void addUserAccount(String password){
        centralBank.addUserAccount(password);
    }

    public void addSavingsAccount(){
        if (currentUserAcc != null && currentUserAcc.getSavingsAccount() == null){
            currentUserAcc.openSavings();
        }

    }

    public void addCheckingAccount(){
        if (currentUserAcc != null && currentUserAcc.getCheckingAccount() == null){
            currentUserAcc.openChecking();
        }

    }


    
}
