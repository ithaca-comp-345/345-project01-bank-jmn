package edu.ithaca.dragon.bank;

public class Admin {

    private CentralBank bank;
    private boolean freezeAccount;

	public Admin(CentralBank bank) {
        freezeAccount = false;
        bank = new CentralBank();
	}


	public void freezeAccount(int id){
        bank.freezeAccount(id);
	}

    public void unfreezeAccount(int id){
        bank.unfreezeAccount(id);
    }


    public boolean getFreezeAccount(){
        return freezeAccount;
    }
    
    

    
}
