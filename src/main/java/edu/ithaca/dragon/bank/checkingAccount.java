package edu.ithaca.dragon.bank;


public class checkingAccount {
    public double balance;
    public boolean isFrozen;
    private String[] checkingTransactions;
    private int arrayLocation = 0;

    public checkingAccount(){
        balance = 0;
        String[] cTransactions = new String[1000];
        checkingTransactions = cTransactions;
    }

    public double getBalance(){
        return balance;
    }

    public String[] getCheckingTransactions(){
        return checkingTransactions;
    }
    
    public void withdraw (double amount) throws InsufficientFundsException{
        if (isFrozen == true){
            return;
        }
        if (UserAccount.isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount entered is not possible to be withdrawn");
        }
        else if (amount <= balance){
            balance -= amount;
            checkingTransactions[arrayLocation] = "Withdraw from checking account of the amount: " + String.valueOf(amount);
            arrayLocation++;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    public void deposit(double amount){
        if (isFrozen == true){
            return;
        }
        if (UserAccount.isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount entered is not possible to be deposited");
        }
        else{
            balance += amount;
            checkingTransactions[arrayLocation] = "Deposit into checking account of the amount: " + String.valueOf(amount);
            arrayLocation++;
        }
    }

    public void transfer(double amount, savingsAccount sAccount)throws InsufficientFundsException{
        if (isFrozen == true){
            return;
        }
        if (UserAccount.isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount entered is not possible to be deposited");
        }
        else if (amount > balance){
            throw new InsufficientFundsException("Not enough money");
        }
        else{
            balance -= amount;
            sAccount.balance += amount;
            checkingTransactions[arrayLocation] = "Transfer from checking account into saving account of the amount: " + String.valueOf(amount);
            arrayLocation++;
        }
    }

    public void freeze(){
        isFrozen = true;
    }

    public void unfreeze(){
        isFrozen = false;
    }
}
