package edu.ithaca.dragon.bank;

import java.util.List;

public class ATM {

    private CentralBank centralBank;
    private UserAccount currentUserAcc;

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

    public double getBalance(String accountType){
        if (currentUserAcc != null){
            if (accountType.equalsIgnoreCase("savings") && currentUserAcc.getSavingsAccount() != null){
                return currentUserAcc.getSavingsAccount().getBalance();
            }
            if (accountType.equalsIgnoreCase("checking") && currentUserAcc.getCheckingAccount() != null){
                return currentUserAcc.getCheckingAccount().getBalance();
            }
            //throw error
            return 0;
        }
        //throw error
        return 0;
    }

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

    public void transfer(int userToDepositTo, String accountTypeToDeposit, double amt, String accountTypeToWithdraw)
            throws InsufficientFundsException {
        UserAccount temp = centralBank.getUserAccount(userToDepositTo);
        if (temp != null){
            if (currentUserAcc != null){
                if (accountTypeToWithdraw.equalsIgnoreCase("savings") && currentUserAcc.getSavingsAccount() != null){
                    currentUserAcc.getSavingsAccount().withdraw(amt);
                    if (accountTypeToDeposit.equalsIgnoreCase("savings") && currentUserAcc.getSavingsAccount() != null){
                        currentUserAcc.getSavingsAccount().deposit(amt);
                    }
                    if (accountTypeToDeposit.equalsIgnoreCase("checking") && currentUserAcc.getCheckingAccount() != null){
                        currentUserAcc.getCheckingAccount().deposit(amt);
                    }
                }
                if (accountTypeToWithdraw.equalsIgnoreCase("checking") && currentUserAcc.getCheckingAccount() != null){
                    currentUserAcc.getCheckingAccount().withdraw(amt);
                    if (accountTypeToDeposit.equalsIgnoreCase("savings") && currentUserAcc.getSavingsAccount() != null){
                        currentUserAcc.getSavingsAccount().deposit(amt);
                    }
                    if (accountTypeToDeposit.equalsIgnoreCase("checking") && currentUserAcc.getCheckingAccount() != null){
                        currentUserAcc.getCheckingAccount().deposit(amt);
                    }
                } 
            }
        }
    }

    public void exitATM(){
        currentUserAcc = null;
    }

    
}
