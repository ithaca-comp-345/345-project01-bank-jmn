package edu.ithaca.dragon.bank;

import java.util.List;

public class ATM {

    protected CentralBank centralBank;
    protected UserAccount currentUserAcc;

    public ATM(CentralBank bank){
        centralBank = bank;
        currentUserAcc = null;
    }
    /**
     * 
     * @param user Acc identifier as entered by Customer
     * @param pass Password as entered by Customer
     * @return true if the password is correct for the given account id 
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
     * 
     * @param accountType string of the account type you want to get balance of
     * @return double of the balance of the given account, returns 0 if the account doesnt exist or the input is wrong
     */
    public double getBalance(String accountType){
        if (currentUserAcc != null){
            if (accountType.equalsIgnoreCase("savings") && currentUserAcc.getSavingsAccount() != null){
                return currentUserAcc.getSavingsAccount().getBalance();
            }
            if (accountType.equalsIgnoreCase("checking") && currentUserAcc.getCheckingAccount() != null){
                return currentUserAcc.getCheckingAccount().getBalance();
            }
            return 0;
        }
        return 0;
    }

    /**
     * 
     * @param amt double to withdraw
     * @param accountType string of the account to withdraw from
     * @throws InsufficientFundsException is thrown if the withdraw more than they have
     */
    public void withdraw(double amt, String accountType) throws InsufficientFundsException {
        if (currentUserAcc != null){
            if (accountType.equalsIgnoreCase("savings") && currentUserAcc.getSavingsAccount() != null){
                currentUserAcc.getSavingsAccount().withdraw(amt);
            }
            if (accountType.equalsIgnoreCase("checking") && currentUserAcc.getCheckingAccount() != null){
                currentUserAcc.getCheckingAccount().withdraw(amt);
            } 
        }
        
    }

    /**
     * 
     * @param amt double to deposit
     * @param accountType string of the account type to deposit to
     */
    public void deposit(double amt, String accountType){
        if (currentUserAcc != null){
            if (accountType.equalsIgnoreCase("savings") && currentUserAcc.getSavingsAccount() != null){
                currentUserAcc.getSavingsAccount().deposit(amt);
            }
            if (accountType.equalsIgnoreCase("checking") && currentUserAcc.getCheckingAccount() != null){
                currentUserAcc.getCheckingAccount().deposit(amt);
            } 
        }
    }

    /**
     * 
     * @param userToTransfer the account to transfer in
     * @param accountTypeToTransfer the account type to withdraw from
     * @param amt the double to transfer
     * @throws InsufficientFundsException
     */
    public void transfer(int userToTransfer, String accountTypeToTransfer, double amt)throws InsufficientFundsException{
        UserAccount temp = centralBank.getUserAccount(userToTransfer);
        if (temp != null){
            if(accountTypeToTransfer.equalsIgnoreCase("checkings")){
                temp.getCheckingAccount().transfer(amt, temp.getSavingsAccount());
            }
            if(accountTypeToTransfer.equalsIgnoreCase("savings")){
                temp.getSavingsAccount().transfer(amt, temp.getCheckingAccount());
            }
        }
    }

    /**
     * Log out of the atm or teller
     */
    public void close(){
        currentUserAcc = null;
    }

    
}
