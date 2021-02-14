package edu.ithaca.dragon.bank;

public class UserAccount {
    private int accountNumber;
    private String password;
    private checkingAccount checkAccount;
    private savingsAccount savAccount;

    public UserAccount(double startingBalance){
        if (BankAccount.isAmountValid(startingBalance)){
            
        }
        else {
            throw new IllegalArgumentException("Starting Balance is invalid, cannot create account");
        }
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void getTransactionHistory(){

    }

    public double getTotalBalance(checkingAccount checkAccount, savingsAccount savAccount){
        double totalBalnce = checkAccount.getBalance() + savAccount.getBalance();
        return totalBalnce;
    }
}
