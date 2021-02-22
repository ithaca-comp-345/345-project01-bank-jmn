package edu.ithaca.dragon.bank;


public class checkingAccount {
    public double balance;
    public boolean isFrozen;
    private String[] checkingTransactions;

    public checkingAccount(){
        balance = 0;
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
            int current = checkingTransactions.length;
            checkingTransactions[current] = "Withdraw from checking account of the amount: " + String.valueOf(amount);
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
            int current = checkingTransactions.length;
            checkingTransactions[current] = "Deposit into checking account of the amount: " + String.valueOf(amount);
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
            int current = checkingTransactions.length;
            checkingTransactions[current] = "Transfer from checking account into saving account of the amount: " + String.valueOf(amount);
        }
    }

    public void freezeUnfreeze(){
        isFrozen = !isFrozen;
    }
}
