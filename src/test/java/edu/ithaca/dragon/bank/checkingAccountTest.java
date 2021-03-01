package edu.ithaca.dragon.bank;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class checkingAccountTest {
    @Test
    void getBalanceTest() {
        checkingAccount cAcc = new checkingAccount();
        cAcc.deposit(200);
        assertEquals(200, cAcc.getBalance()); //positve integer equivalence class
        checkingAccount cAcc2 = new checkingAccount();
        assertEquals(0, cAcc2.getBalance()); //0 equivalence class, also a border case which is the reason for the values of next two tests
        checkingAccount cAcc3 = new checkingAccount();
        cAcc3.deposit(1);
        assertEquals(1, cAcc3.getBalance()); //positive integer equivalence class
        checkingAccount cAcc4 = new checkingAccount();
        cAcc4.deposit(3.5);
        assertEquals(3.5, cAcc4.getBalance()); //positive double equivalence class
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        checkingAccount cAcc = new checkingAccount();
        cAcc.deposit(200);
        cAcc.withdraw(100); 
        assertEquals(100, cAcc.getBalance());// equivalence class of positive integer amount withdrawn less than positive integer balance
        cAcc.withdraw(5.2);
        assertEquals(94.8, cAcc.getBalance());// equivalence class of positive double amount withdrawn less than positive integer amount
        assertThrows(InsufficientFundsException.class, () -> cAcc.withdraw(300));

        checkingAccount cAcc2 = new checkingAccount();
        cAcc2.withdraw(0); //equivalence class of 0 or even amounts
        assertEquals(0, cAcc2.getBalance());
        assertThrows(InsufficientFundsException.class, () -> cAcc2.withdraw(1)); // equivalence class of positive integer greater than balance
        assertThrows(IllegalArgumentException.class, () -> cAcc2.withdraw(-1)); // equivalence class of negative number

        checkingAccount cAcc3 = new checkingAccount();
        cAcc3.deposit(35.32);
        cAcc3.withdraw(20.18); // equivalence class of positive double amount withdrawn less than positive double balance
        assertEquals(15.14, cAcc3.getBalance()); 
        assertThrows(InsufficientFundsException.class, () -> cAcc3.withdraw(20.5));// equivalence class of positive double amount withdrawn greater than positive double balance

        checkingAccount cAcc4 = new checkingAccount();
        cAcc4.deposit(35.75);
        cAcc4.withdraw(20); // equivalence class of positive integer amount withdrawn less than positive double balance
        assertEquals(15.75, cAcc4.getBalance()); 
        assertThrows(InsufficientFundsException.class, () -> cAcc4.withdraw(20));// equivalence class of positive integer amount withdrawn greater than positive double balance
        assertThrows(IllegalArgumentException.class, () -> cAcc4.withdraw(-5.2));
        assertThrows(IllegalArgumentException.class, () -> cAcc4.withdraw(-12.36));
        assertThrows(IllegalArgumentException.class, () -> cAcc4.withdraw(-27.874));
        assertThrows(IllegalArgumentException.class, () -> cAcc4.withdraw(53.923));

    }

    @Test
    void depositTest(){
        checkingAccount cAcc = new checkingAccount();
        cAcc.deposit(200);
        cAcc.deposit(100.2); 
        assertEquals(300.2, cAcc.getBalance());//equivalence class of valid positive double with one decimal place
        cAcc.deposit(5.23);
        assertEquals(305.43, cAcc.getBalance());//equivalence class of valid positive double with two decimal place
        cAcc.deposit(0);//border case; equivalence class of valid non-negative number
        assertEquals(305.43, cAcc.getBalance());
        assertThrows(IllegalArgumentException.class, () -> cAcc.deposit(476.456));//equivalence class of invalid positive double with more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () -> cAcc.deposit(-1));//equivalence class of invalid negative double with 0 decimal places (essentially int)
        assertThrows(IllegalArgumentException.class, () -> cAcc.deposit(-21.3));//equivalence class of invalid negative double with 1 decimal places 
        assertThrows(IllegalArgumentException.class, () -> cAcc.deposit(-14.63));//equivalence class of invalid negative double with 2 decimal places 
        assertThrows(IllegalArgumentException.class, () -> cAcc.deposit(-48.102));//equivalence class of invalid negative double with more than 2 decimal places 
    }


    @Test
    void transferTest() throws InsufficientFundsException{
        checkingAccount cAcc = new checkingAccount();
        cAcc.deposit(200);
        savingsAccount sAcc = new savingsAccount();
        sAcc.deposit(50);
        cAcc.transfer(50, sAcc);//equivalence class of valid positive double with no decimal place and sufficient funds
        assertEquals(150, cAcc.getBalance());//equivalence class of valid positive double with no decimal place and sufficient funds
        assertEquals(100, sAcc.getBalance());
        cAcc.transfer(30.5, sAcc);//equivalence class of valid positive double with one decimal place and sufficient funds
        assertEquals(119.5, cAcc.getBalance());
        assertEquals(130.5, sAcc.getBalance());
        cAcc.transfer(19.25, sAcc);//equivalence class of valid positive double with two decimal places and sufficient funds
        assertEquals(100.25, cAcc.getBalance());
        assertEquals(149.75, sAcc.getBalance());
        assertThrows(IllegalArgumentException.class, () -> cAcc.transfer(80.874, sAcc));//equivalence class of invalid arguments because the amount is has more than two decimal points
        cAcc.transfer(0, sAcc);//border case of 0; equivalence class of valid non-negative argument
        assertEquals(100.25, cAcc.getBalance());
        assertEquals(149.75, sAcc.getBalance());
        assertThrows(IllegalArgumentException.class, () -> cAcc.transfer(-1, sAcc));//equivalence class of invalid arguments because the amount is negative
        assertThrows(IllegalArgumentException.class, () -> cAcc.transfer(-12.3, sAcc));//equivalence class of invalid arguments because the amount is negative and has 1 decimal point
        assertThrows(IllegalArgumentException.class, () -> cAcc.transfer(-23.42, sAcc));//equivalence class of invalid arguments because the amount is negative and has 2 decimal points
        assertThrows(IllegalArgumentException.class, () -> cAcc.transfer(-833.872, sAcc));//equivalence class of invalid arguments because the amount is negative and has more than 2 decimal points
        assertThrows(InsufficientFundsException.class, () -> cAcc.transfer(300.21, sAcc));// equivalence class of invalid amount entered because funds are too low to cover it
    }

    @Test

    void getCheckingTransactionsTest(){
        checkingAccount cAcc = new checkingAccount();
        String[] check = cAcc.getCheckingTransactions();
        assertNull(check[0]); //Equivalence Class: Nothing Added to array yet
        cAcc.deposit(200);
        assertNotNull(check[0]); //Equivalence Class: One thing added to array
        cAcc.deposit(200);
        assertNotNull(check[1]); //Equivalence Class: Multiple things added to array
    }
}
