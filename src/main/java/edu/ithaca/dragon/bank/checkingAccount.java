package edu.ithaca.dragon.bank;


public class checkingAccount {
    private double balance;
    private boolean isFrozen;
    private String[] checkingTransactions;

    public double getBalance(){
        return balance;
    }

    public void withdraw (double amount) throws InsufficientFundsException{
        if (BankAccount.isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount entered is not possible to be withdrawn");
        }
        else if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    public void deposit(double amount){
        if (BankAccount.isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount entered is not possible to be deposited");
        }
        else{
            balance += amount;
        }
    }

    public void transfer(double amount, BankAccount transferAccount, BankAccount otherAccount)throws InsufficientFundsException{
        if (BankAccount.isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount entered is not possible to be deposited");
        }
        else if (transferAccount == otherAccount){
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        else if (amount > transferAccount.balance){
            throw new InsufficientFundsException("Not enough money");
        }
        else{
            transferAccount.balance -= amount;
            otherAccount.balance += amount;
        }
    }
}
