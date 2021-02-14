package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

    @Test
    void isAmountValidTest(){
        assertTrue(BankAccount.isAmountValid(16.20)); //Equivalence Class: Valid Positive Double with two decimal places
        assertTrue(BankAccount.isAmountValid(8.6)); //Equivalence Class: Valid Positive Double with one decimal place
        assertTrue(BankAccount.isAmountValid(3)); //Equivalence Class: Valid Positive Double with 0 decimal places (essentially an int)
        assertTrue(BankAccount.isAmountValid(0)); //Border Case: on the border between positive and negative Equivalence Class: Valid non-negative Double with 0 decimal places (essentially an int)
        assertFalse(BankAccount.isAmountValid(-1)); //Equivalence Class: Invalid Negative Double with 0 decimal places (essentially an int)
        assertFalse(BankAccount.isAmountValid(-11.6)); //Equivalence Class: Invalid Negative Double with 1 decimal place
        assertFalse(BankAccount.isAmountValid(-23.67)); //Equivalence Class: Invalid Negative Double with 2 decimal places
        assertFalse(BankAccount.isAmountValid(16.204)); //Equivalence Class: Invalid Positive Double with more than 2 decimal places
        assertFalse(BankAccount.isAmountValid(-37.675)); //Equivalence Class: Invalid Negative Double with more than 2 decimal places
    }

}