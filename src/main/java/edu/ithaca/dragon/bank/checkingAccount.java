package edu.ithaca.dragon.bank;


public class checkingAccount {
    public double balance;
    public boolean isFrozen;
    private String[] checkingTransactions;
    private int arrayLocation = 0;

    /**
     * Constructor for checking account
     * initializes balance to 0 and creates checking transaction history array of size 1000
     */
    public checkingAccount(){
        balance = 0;
        String[] cTransactions = new String[1000];
        checkingTransactions = cTransactions;
    }

    /**
     * getter for balance property
     * @return balance of checking account
     */
    public double getBalance(){
        return balance;
    }

    /**
     * getter of checkingTransactions property
     * @return checkingTransactions history array
     */
    public String[] getCheckingTransactions(){
        return checkingTransactions;
    }
    
    /**
     * withdraws (subtracts) specified amount from checking account balance
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
        else if (amount <= balance){
            balance -= amount;
            checkingTransactions[arrayLocation] = "Withdraw from checking account of the amount: " + String.valueOf(amount);
            arrayLocation++;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    /**
     * deposits (adds) specified amount to checking account balance
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
            checkingTransactions[arrayLocation] = "Deposit into checking account of the amount: " + String.valueOf(amount);
            arrayLocation++;
        }
    }

    /**
     * transfer a specified amount to specified savings account
     * subtract that amount from checking account balance, add that amount to savings account balance
     * @param amount to be transferred
     * @param sAccount to transfer to
     * @throws InsufficientFundsException thrown if checking account balance is smaller than amount to be transferred
     */
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
