package edu.ithaca.dragon.bank;

public class BankTeller extends ATM{

    /**
     * Constructor of bank teller
     * @param bank the bank we want to connect to
     */
    public BankTeller(CentralBank bank){
        super(bank);
    }

    /**
     * Add a user account to the back
     * @param password the pin for the user account
     */
    public void addUserAccount(String password){
        centralBank.addUserAccount(password);
    }

    /**
     * Same function as in ATM 
     */
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

    /**
     * Add an user account to the bank
     * @param password string pin for the user account
     * @param id int id for the user account
     */
    public void addUserAccount(String password, int id){
        centralBank.addUserAccount(password, id);
    }

    /**
     * Open a savings account for the logged in account
     */
    public void addSavingsAccount(){
        if (currentUserAcc != null && currentUserAcc.getSavingsAccount() == null){
            currentUserAcc.openSavings();
        }

    }

    /**
     * Open a checking account for the logged in account
     */
    public void addCheckingAccount(){
        if (currentUserAcc != null && currentUserAcc.getCheckingAccount() == null){
            currentUserAcc.openChecking();
        }

    }


    
}
