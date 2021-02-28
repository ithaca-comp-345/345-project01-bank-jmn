package edu.ithaca.dragon.bank;

public class Admin {

    private CentralBank bank;
    private boolean freezeAccount;
    private UserAccount accountNum;

	public Admin(int num) {
        freezeAccount = false;
        accountNum = bank.getUserAccount(num);
	}


	public boolean freezeOrUnFreezeAccount(boolean freezeOrUnFreeze){
        return true;
	}

    public int getAccountNumber() {
        return accountNum.getAccountNumber();
    }

    public boolean getFreezeAccount(){
        return getFreezeAccount();
    }
    
    

    
}
