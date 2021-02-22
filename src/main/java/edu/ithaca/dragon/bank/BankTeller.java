package edu.ithaca.dragon.bank;

public class BankTeller extends ATM{


    public BankTeller(CentralBank bank){
        super(bank);
    }

    public void addUserAccount(String password){
        centralBank.addUserAccount(password);
    }

    public boolean confirmCredentials(int user, String pass){
        UserAccount temp = centralBank.getUserAccount(user);
        if (temp != null)
        {
            if (temp.getPassword().equals(pass)){
                currentUserAcc = temp;
                return true;
            }
        }
        //throw error
        currentUserAcc = null;
        return false;
    }

    public void addUserAccount(String password, int id){
        centralBank.addUserAccount(password, id);
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
