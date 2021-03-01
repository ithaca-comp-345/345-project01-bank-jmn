package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.List;

public class CentralBank {

    private List<UserAccount> userAccounts;

    /**
     * Constructor that creates the array list of accounts
     */
    public CentralBank(){
        userAccounts = new ArrayList<UserAccount>();
    }

    /**
     * 
     * @param id the id of the account to look for
     * @return the account with the given id if found or null if not found
     */
    public UserAccount getUserAccount(int id){
        for (int i =0; i < userAccounts.size(); i++){
            if (userAccounts.get(i).getAccountNumber() == id){
                return userAccounts.get(i);
            }
        }
        return null;
    }

    /**
     * Given a pin creates a user with a random account number
     * @param password string of the pin of the account
     */
    public void addUserAccount(String password){
        userAccounts.add(new UserAccount(password));
    }

    /**
     * More customizable account creation
     * @param password the string of the pin for the account
     * @param id the int of the id number of the account
     */
    public void addUserAccount(String password, int id){
        userAccounts.add(new UserAccount(password, id));
    }
    
    /**
     * Freeze account function
     * @param id the id of the account to freeze
     */
    public void freezeAccount(int id){
        UserAccount temp = getUserAccount(id);
        temp.getCheckingAccount().freeze();
        temp.getSavingsAccount().freeze();
    }

    /**
     * Unfreeze Account 
     * @param id the id of the account to unfreeze
     */
    public void unfreezeAccount(int id){
        UserAccount temp = getUserAccount(id);
        temp.getCheckingAccount().unfreeze();
        temp.getSavingsAccount().unfreeze();
    }


}
