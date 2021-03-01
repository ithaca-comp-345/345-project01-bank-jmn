package edu.ithaca.dragon.bank;

public class Admin {

    private CentralBank newbank;
    private boolean freezeAccount;

	public Admin(CentralBank bank) {
        freezeAccount = false;
        newbank = bank;   
	}


	public void freezeAccount(int id){
        newbank.freezeAccount(id);
	}

    public void unfreezeAccount(int id){
        newbank.unfreezeAccount(id);
    }


    public boolean getFreezeAccount(){
        return freezeAccount;
    }
    
    

    
}
