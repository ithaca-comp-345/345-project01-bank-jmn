package edu.ithaca.dragon.bank;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserAccountTest{
    @Test
    void isAmountValidTest(){
        assertTrue(UserAccount.isAmountValid(16.20)); //Equivalence Class: Valid Positive Double with two decimal places
        assertTrue(UserAccount.isAmountValid(8.6)); //Equivalence Class: Valid Positive Double with one decimal place
        assertTrue(UserAccount.isAmountValid(3)); //Equivalence Class: Valid Positive Double with 0 decimal places (essentially an int)
        assertTrue(UserAccount.isAmountValid(0)); //Border Case: on the border between positive and negative Equivalence Class: Valid non-negative Double with 0 decimal places (essentially an int)
        assertFalse(UserAccount.isAmountValid(-1)); //Equivalence Class: Invalid Negative Double with 0 decimal places (essentially an int)
        assertFalse(UserAccount.isAmountValid(-11.6)); //Equivalence Class: Invalid Negative Double with 1 decimal place
        assertFalse(UserAccount.isAmountValid(-23.67)); //Equivalence Class: Invalid Negative Double with 2 decimal places
        assertFalse(UserAccount.isAmountValid(16.204)); //Equivalence Class: Invalid Positive Double with more than 2 decimal places
        assertFalse(UserAccount.isAmountValid(-37.675)); //Equivalence Class: Invalid Negative Double with more than 2 decimal places
    }

    @Test

    void getTotalBalanceTest(){
        UserAccount testUser = new UserAccount("3333", 10);
        assertThrows(IllegalArgumentException.class, () -> testUser.getTotalBalance(testUser.getCheckingAccount(), testUser.getSavingsAccount()));//equivalence class of invalid arguments because the accounts must exist to calculate total balance
        testUser.openChecking();
        assertEquals(0,testUser.getTotalBalance(testUser.getCheckingAccount(), testUser.getSavingsAccount())); //Equivalence Class: Only one account is initialized
        testUser.openSavings();
        assertEquals(0,testUser.getTotalBalance(testUser.getCheckingAccount(), testUser.getSavingsAccount())); //Equivalence Class: Both accounts have just been initialized/have balances of 0
        testUser.getCheckingAccount().deposit(35);
        assertEquals(35,testUser.getTotalBalance(testUser.getCheckingAccount(), testUser.getSavingsAccount())); //Equivalence Class: Only One account has value greater than 0
        testUser.getSavingsAccount().deposit(50);
        assertEquals(85,testUser.getTotalBalance(testUser.getCheckingAccount(), testUser.getSavingsAccount())); //Equivalence Class: Both Accounts have values greater than 0
    }

    @Test

    void getTransactionHistoryTest(){ //This is an integration test because getTransactionHistory relies on calls to the checking and savings class to get their specific transaction histories
        UserAccount testUser = new UserAccount("3333", 10);
        testUser.openChecking();
        testUser.openSavings();
        assertEquals(0, testUser.getTransactionHistory()); //Equivalence Class: No transactions at all
        testUser.getCheckingAccount().deposit(20);
        assertEquals(1, testUser.getTransactionHistory()); //Equivalence Class: A single transaction from checking
        testUser.getCheckingAccount().deposit(5);
        assertEquals(2, testUser.getTransactionHistory()); //Equivalence Class: Multiple transactions from checking
        testUser.getSavingsAccount().deposit(10);
        assertEquals(3, testUser.getTransactionHistory()); //Equivalence Class: Multiple transactions from checking and a single transaction from savings
        testUser.getSavingsAccount().deposit(20);;
        assertEquals(4, testUser.getTransactionHistory()); // Equivalence Class: Multiple Transactions from both checking and savings
    }
 }
