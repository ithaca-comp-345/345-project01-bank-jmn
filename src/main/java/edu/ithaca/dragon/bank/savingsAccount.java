package edu.ithaca.dragon.bank;

public class savingsAccount {
    public double balance;
    private Double dailyMaxWithdraw = null;
    private double todayWithdraw;
    public boolean isFrozen;
    public double interestRate;
    private int arrayLocation = 0;
    private String[] savingTransactions;

    /**
     * Constructor for savings account
     * initializes balance to 0 and creates savings transaction history array of size 1000
     */
    public savingsAccount(){
        balance = 0;
        String[] sTransactions = new String[1000];
        savingTransactions = sTransactions;
    }

    /**
     * getter for balance property
     * @return balance of savings account
     */
    public double getBalance(){
        return balance;
    }

    /**
     * getter of savingTransactions property
     * @return savingTransactions history array
     */
    public String[] getSavingTransactions(){
        return savingTransactions;
    }

    /**
     * setter to change dailyMaxWithdraw property to specified amount
     * @param amount is the dailyMaxWithdraw level
     */
    public void setDailyMaxWithdraw(double amount){
        this.dailyMaxWithdraw = amount;
    }

    /**
     * calculates total balance with interest included based on current interest rate and balance
     * @return total balance with interest included
     */
    public double calcInterest(){
        double interest = (interestRate + 1) * balance;
        return interest;
    }

    /**
     * withdraws (subtracts) specified amount from savings account balance
     * @param amount to be withdrawn
     * @throws InsufficientFundsException thrown if balance is smaller than amount entered to be withdrawn
     */
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

    /**
     * deposits (adds) specified amount to savings account balance
     * @param amount to be deposited
     */
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

    /**
     * transfer a specified amount to specified checking account
     * subtract that amount from savings account balance, add that amount to checking account balance
     * @param amount to be transferred
     * @param sAccount to transfer to
     * @throws InsufficientFundsException thrown if saving account balance is smaller than amount to be transferred
     */
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

    /**
     * setter that changes isFrozen property to true
     */
    public void freeze(){
        isFrozen = true;
    }

    /**
     * setter that changes isFrozen property to false
     */
    public void unfreeze(){
        isFrozen = false;
    }
}
