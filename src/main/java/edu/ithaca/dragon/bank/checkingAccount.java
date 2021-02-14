package edu.ithaca.dragon.bank;


public class checkingAccount {
    private double balance;
    private boolean isFrozen;


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
}
