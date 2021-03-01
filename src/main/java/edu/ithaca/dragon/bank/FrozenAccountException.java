package edu.ithaca.dragon.bank;

public class FrozenAccountException extends Exception{
    private static final long serialVersionUID = 1L;

    public FrozenAccountException(String s) {
        super(s);
    }

}