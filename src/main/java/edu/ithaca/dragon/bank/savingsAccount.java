package edu.ithaca.dragon.bank;

public class savingsAccount {
    public double balance;
    private Double dailyMaxWithdraw = null;
    private double todayWithdraw;
    public boolean isFrozen;
    private double interestRate;
    private int arrayLocation = 0;
    private String[] savingTransactions;

    public savingsAccount(){
        balance = 0;
        String[] sTransactions = new String[1000];
        savingTransactions = sTransactions;
    }

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
        if (UserAccount.isAmountValid(amount) == false){
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
            savingTransactions[arrayLocation] = "Withdraw from savings account of the amount: " + String.valueOf(amount);
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
            savingTransactions[arrayLocation]= "Deposit into savings account of the amount: " + String.valueOf(amount);
            arrayLocation++;
        }
    }

    public void transfer(double amount, checkingAccount cAccount)throws InsufficientFundsException{
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
            cAccount.balance += amount;
            savingTransactions[arrayLocation] = "Transfer from savings account into checkings account of the amount: " + String.valueOf(amount);
            arrayLocation++;
        }
    }

    public void freezeUnfreeze(){
        isFrozen = !isFrozen;
    }
}
