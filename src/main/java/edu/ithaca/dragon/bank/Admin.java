package edu.ithaca.dragon.bank;

public class Admin {

    private CentralBank bank;
    private boolean freezeAccount;
    private UserAccount accountNum;

	public Admin(int num) {
        freezeAccount = false;
        bank = new CentralBank();
        accountNum = bank.getUserAccount(num);
	}


	public void freezeOrUnFreezeAccount(boolean freezeOrUnFreeze){
        if(freezeOrUnFreeze == true){
            freezeAccount = true;
        }
        else{
            freezeAccount = false;
        }
	}

    public int getAccountNumber() {
        return accountNum.getAccountNumber();
    }

    public boolean getFreezeAccount(){
        return freezeAccount;
    }
    
    

    
}
