package edu.ithaca.dragon.bank;
import java.util.Random;

public class UserAccount {
    private int accountNumber;
    private String password;
    private checkingAccount checkAccount = null;
    private savingsAccount savAccount = null;
    public boolean isFrozen = false;

    /**
     * Constructor that creates a random account number for user
     * @param pin (password)
     */
    public UserAccount(String pin){
        Random rand = new Random();
        this.password = pin;
        accountNumber = rand.nextInt(999999999 - 100000000) + 100000000;
    }

    /**
     * Constructor that creates a sepcified account number for user
     * @param pin (password)
     * @param accountNum
     */
    public UserAccount(String pin, int accountNum){
        this.password = pin;
        accountNumber = accountNum;
    }

    /**
     * Grants a savings account to a user and replaces the null value in the savingsAccount Property
     */
    public void openSavings(){
        savAccount = new savingsAccount();
    }

     /**
     * Grants a checking account to a user and replaces the null value in the checkingAccount Property
     */
    public void openChecking(){
        checkAccount = new checkingAccount();
    }

    /**
     * getter for account number property
     * @return account number of instantiated userAccount
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * getter of password property
     * @return password of instantiated userAccount
     */
    public String getPassword() {
        return password;
    }

    /**
     * getter of checkingAccount property
     * @return checkingAccount property of instantiated userAccount
     */
    public checkingAccount getCheckingAccount() {
        return checkAccount;
    }

    /**
     * getter of savingsAccount property
     * @return savingdAccount property of instantiated userAccount
     */
    public savingsAccount getSavingsAccount() {
        return savAccount;
    }

    /**
     * cycles through transaction history arrays, printing values at each index
     * utilzing getCheckingTransactions & getSavingsTransactions 
     */
    public void getTransactionHistory(){
        if (checkAccount != null){
            String[] cAccount = checkAccount.getCheckingTransactions();
            for (int i = 0; i < cAccount.length; i++){
                System.out.println(cAccount[i]);
             }
        }
        if (savAccount != null){
            String[] sAccount = savAccount.getSavingTransactions();
            for (int i = 0; i < sAccount.length; i++){
                System.out.println(sAccount[i]);
             }
        }
    }

    /**
     * adds balances of checking and savings account to get total Balance
     * @param checkAccount
     * @param savAccount
     * @return combined balance/single balance if one of parameters is null
     */
    public double getTotalBalance(checkingAccount checkAccount, savingsAccount savAccount){
        if(checkAccount == null && savAccount != null){
            return savAccount.getBalance();
        }
        if(checkAccount != null && savAccount == null){
            return checkAccount.getBalance();
        }
        if(checkAccount == null && savAccount == null){
            throw new IllegalArgumentException("You must have an account to get the balance of"); //if both accounts are null no balance can be returned
        }
        double totalBalnce = checkAccount.getBalance() + savAccount.getBalance();
        return totalBalnce;
    }

    /**
     * checks if amount entered is a valid positive double amount
     * @param amount to be tested
     * @return true if amount is valid false if not
     */
    public static boolean isAmountValid(double amount){
        String amountStr = String.valueOf(amount);
        if(amount < 0){
            return false;
        }
        else if (amountStr.indexOf('.') == -1 ){
            return true;
        }
        else{
            String decimal = amountStr.substring(amountStr.indexOf('.') + 1);
            if(decimal.length() > 2){
                return false;
            }
            else{
                return true;
            }
        }

    }
}
