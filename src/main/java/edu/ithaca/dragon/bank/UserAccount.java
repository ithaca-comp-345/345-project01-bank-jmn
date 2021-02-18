package edu.ithaca.dragon.bank;
import java.util.Random;

public class UserAccount {
    private int accountNumber;
    private String password;
    private checkingAccount checkAccount = null;
    private savingsAccount savAccount = null;
    public boolean isFrozen = false;

    public UserAccount(String pin){
        Random rand = new Random();
        this.password = pin;
        accountNumber = rand.nextInt(999999999 - 100000000) + 100000000;
    }

    public void openSavings(){
        savAccount = new savingsAccount();
    }

    public void openChecking(){
        checkAccount = new checkingAccount();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public checkingAccount getCheckingAccount() {
        return checkAccount;
    }

    public savingsAccount getSavingsAccount() {
        return savAccount;
    }

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

    public double getTotalBalance(checkingAccount checkAccount, savingsAccount savAccount){
        double totalBalnce = checkAccount.getBalance() + savAccount.getBalance();
        return totalBalnce;
    }
}
