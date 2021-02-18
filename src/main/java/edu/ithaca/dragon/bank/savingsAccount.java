package edu.ithaca.dragon.bank;

public class savingsAccount {
    public double balance;
    private Double dailyMaxWithdraw = null;
    private double todayWithdraw;
    public boolean isFrozen;
    private double interestRate;
    private String[] savingTransactions;

    public double getBalance(){
        return balance;
    }

    public String[] getSavingTransactions(){
        return savingTransactions;
    }

    public void setDailyMaxWithdraw(double amount){
        this.dailyMaxWithdraw = amount;
    }

    public double calcInterest(){
        double interest = (interestRate + 1) * balance;
        return interest;
    }

    public void withdraw (double amount) throws InsufficientFundsException{
        if (isFrozen == true){
            return;
        }
        if (BankAccount.isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount entered is not possible to be withdrawn");
        }
        if (dailyMaxWithdraw != null){
            todayWithdraw += amount;
            if(todayWithdraw > dailyMaxWithdraw){
                throw new IllegalArgumentException("Amount entered would exceed your daily withdraw limit");
            }
        }
        else if (amount <= balance){
            balance -= amount;
            int current = savingTransactions.length;
            savingTransactions[current] = "Withdraw from savings account of the amount: " + String.valueOf(amount);
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    public void deposit(double amount){
        if (isFrozen == true){
            return;
        }
        if (BankAccount.isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount entered is not possible to be deposited");
        }
        else{
            balance += amount;
            int current = savingTransactions.length;
            savingTransactions[current] = "Deposit into savings account of the amount: " + String.valueOf(amount);
        }
    }

    public void transfer(double amount, checkingAccount cAccount)throws InsufficientFundsException{
        if (isFrozen == true){
            return;
        }
        if (BankAccount.isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount entered is not possible to be deposited");
        }
        else if (amount > balance){
            throw new InsufficientFundsException("Not enough money");
        }
        else{
            balance -= amount;
            cAccount.balance += amount;
            int current = savingTransactions.length;
            savingTransactions[current] = "Transfer from savings account into checkings account of the amount: " + String.valueOf(amount);
        }
    }

    public void freezeUnfreeze(){
        isFrozen = !isFrozen;
    }
}
