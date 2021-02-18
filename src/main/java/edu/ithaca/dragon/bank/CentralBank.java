package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.List;

public class CentralBank {

    private List<UserAccount> userAccounts;

    public CentralBank(){
        userAccounts = new ArrayList<UserAccount>(){
            
        };
    }

    public UserAccount getUserAccount(int id){
        for (int i =0; i < userAccounts.size(); i++){
            if (userAccounts.get(i).getAccountNumber() == id){
                return userAccounts.get(i);
            }
        }
        return null;
    }

    public void addUserAccount(String password){
        userAccounts.add(new UserAccount(password));
    }
    
    public void freezeUnfreeze(UserAccount account){
        account.isFrozen = true;
        account.freezeUnfreezeAccts(account.getCheckingAccount(), account.getSavingsAccount());
    }

}
