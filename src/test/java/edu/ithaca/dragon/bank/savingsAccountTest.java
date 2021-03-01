package edu.ithaca.dragon.bank;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class savingsAccountTest {
    @Test
    void getBalanceTest() {
        savingsAccount sAcc = new savingsAccount();
        sAcc.deposit(200);
        assertEquals(200, sAcc.getBalance()); //positve integer equivalence class
        savingsAccount sAcc2 = new savingsAccount();
        assertEquals(0, sAcc2.getBalance()); //0 equivalence class, also a border case which is the reason for the values of next two tests
        savingsAccount sAcc3 = new savingsAccount();
        sAcc3.deposit(1);
        assertEquals(1, sAcc3.getBalance()); //positive integer equivalence class
        savingsAccount sAcc4 = new savingsAccount();
        sAcc4.deposit(3.5);
        assertEquals(3.5, sAcc4.getBalance()); //positive double equivalence class
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        savingsAccount sAcc = new savingsAccount();
        sAcc.deposit(200);
        sAcc.withdraw(100); 
        assertEquals(100, sAcc.getBalance());// equivalence class of positive integer amount withdrawn less than positive integer balance
        sAcc.withdraw(5.2);
        assertEquals(94.8, sAcc.getBalance());// equivalence class of positive double amount withdrawn less than positive integer amount
        assertThrows(InsufficientFundsException.class, () -> sAcc.withdraw(300));

        savingsAccount sAcc2 = new savingsAccount();
        sAcc2.withdraw(0); //equivalence class of 0 or even amounts
        assertEquals(0, sAcc2.getBalance());
        assertThrows(InsufficientFundsException.class, () -> sAcc2.withdraw(1)); // equivalence class of positive integer greater than balance
        assertThrows(IllegalArgumentException.class, () -> sAcc2.withdraw(-1)); // equivalence class of negative number

        savingsAccount sAcc3 = new savingsAccount();
        sAcc3.deposit(35.32);
        sAcc3.withdraw(20.18); // equivalence class of positive double amount withdrawn less than positive double balance
        assertEquals(15.14, sAcc3.getBalance()); 
        assertThrows(InsufficientFundsException.class, () -> sAcc3.withdraw(20.5));// equivalence class of positive double amount withdrawn greater than positive double balance

        savingsAccount sAcc4 = new savingsAccount();
        sAcc4.deposit(35.75);
        sAcc4.withdraw(20); // equivalence class of positive integer amount withdrawn less than positive double balance
        assertEquals(15.75, sAcc4.getBalance()); 
        assertThrows(InsufficientFundsException.class, () -> sAcc4.withdraw(20));// equivalence class of positive integer amount withdrawn greater than positive double balance
        assertThrows(IllegalArgumentException.class, () -> sAcc4.withdraw(-5.2));
        assertThrows(IllegalArgumentException.class, () -> sAcc4.withdraw(-12.36));
        assertThrows(IllegalArgumentException.class, () -> sAcc4.withdraw(-27.874));
        assertThrows(IllegalArgumentException.class, () -> sAcc4.withdraw(53.923));

    }

    @Test
    void depositTest(){
        savingsAccount sAcc = new savingsAccount();
        sAcc.deposit(200);
        sAcc.deposit(100.2); 
        assertEquals(300.2, sAcc.getBalance());//equivalence class of valid positive double with one decimal place
        sAcc.deposit(5.23);
        assertEquals(305.43, sAcc.getBalance());//equivalence class of valid positive double with two decimal place
        sAcc.deposit(0);//border case; equivalence class of valid non-negative number
        assertEquals(305.43, sAcc.getBalance());
        assertThrows(IllegalArgumentException.class, () -> sAcc.deposit(476.456));//equivalence class of invalid positive double with more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () -> sAcc.deposit(-1));//equivalence class of invalid negative double with 0 decimal places (essentially int)
        assertThrows(IllegalArgumentException.class, () -> sAcc.deposit(-21.3));//equivalence class of invalid negative double with 1 decimal places 
        assertThrows(IllegalArgumentException.class, () -> sAcc.deposit(-14.63));//equivalence class of invalid negative double with 2 decimal places 
        assertThrows(IllegalArgumentException.class, () -> sAcc.deposit(-48.102));//equivalence class of invalid negative double with more than 2 decimal places 
    }


    @Test
    void transferTest() throws InsufficientFundsException{
        savingsAccount sAcc = new savingsAccount();
        sAcc.deposit(200);
        checkingAccount cAcc = new checkingAccount();
        cAcc.deposit(50);
        sAcc.transfer(50, cAcc);//equivalence class of valid positive double with no decimal place and sufficient funds
        assertEquals(150, sAcc.getBalance());//equivalence class of valid positive double with no decimal place and sufficient funds
        assertEquals(100, cAcc.getBalance());
        sAcc.transfer(30.5, cAcc);//equivalence class of valid positive double with one decimal place and sufficient funds
        assertEquals(119.5, sAcc.getBalance());
        assertEquals(130.5, cAcc.getBalance());
        sAcc.transfer(19.25, cAcc);//equivalence class of valid positive double with two decimal places and sufficient funds
        assertEquals(100.25, sAcc.getBalance());
        assertEquals(149.75, cAcc.getBalance());
        assertThrows(IllegalArgumentException.class, () -> sAcc.transfer(80.874, cAcc));//equivalence class of invalid arguments because the amount is has more than two decimal points
        sAcc.transfer(0, cAcc);//border case of 0; equivalence class of valid non-negative argument
        assertEquals(100.25, sAcc.getBalance());
        assertEquals(149.75, cAcc.getBalance());
        assertThrows(IllegalArgumentException.class, () -> sAcc.transfer(-1, cAcc));//equivalence class of invalid arguments because the amount is negative
        assertThrows(IllegalArgumentException.class, () -> sAcc.transfer(-12.3, cAcc));//equivalence class of invalid arguments because the amount is negative and has 1 decimal point
        assertThrows(IllegalArgumentException.class, () -> sAcc.transfer(-23.42, cAcc));//equivalence class of invalid arguments because the amount is negative and has 2 decimal points
        assertThrows(IllegalArgumentException.class, () -> sAcc.transfer(-833.872, cAcc));//equivalence class of invalid arguments because the amount is negative and has more than 2 decimal points
        assertThrows(InsufficientFundsException.class, () -> sAcc.transfer(300.21, cAcc));// equivalence class of invalid amount entered because funds are too low to cover it
    }

    @Test

    void getSavingsTransactionsTest(){
        savingsAccount sAcc = new savingsAccount();
        String[] check = sAcc.getSavingTransactions();
        assertNull(check[0]); //Equivalence Class: Nothing Added to array yet
        sAcc.deposit(200);
        assertNotNull(check[0]); //Equivalence Class: One thing added to array
        sAcc.deposit(200);
        assertNotNull(check[1]); //Equivalence Class: Multiple things added to array
    }

    @Test

    void calcInterestTest(){
        savingsAccount sAcc = new savingsAccount();
        sAcc.deposit(10);
        sAcc.interestRate = .1;
        assertEquals(11, sAcc.calcInterest()); //Equivalence Class: balance and interested rate both hold values
        sAcc.withdraw(10);
        assertEquals(0, sAcc.calcInterest()); //Equivalence Class: balance is 0 and interest rate holds a value
        sAcc.interestRate = 0;
        assertEquals(0, sAcc.calcInterest()); //Equivalence Class: neither balance or interest rate hold a value
    }
}
