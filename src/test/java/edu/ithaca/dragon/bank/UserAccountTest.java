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

    @Test

    void userAccountSystemTest(){ //This is a systems test because it is testing the ability to go through with various actions such as opening accounts, getting history, and transactions whether they be checking transactions or 
        UserAccount testUser = new UserAccount("1111", 8); // savings transactions. Knowing if these work will tell us if whatever system we utilize this java code in will be able to utilize all of this functionnality properly
        assertNull(testUser.getCheckingAccount()); // Equivalence Class: No checking Account yet
        assertNull(testUser.getSavingsAccount()); // Equivalence Class: No checking Account yet
        testUser.openChecking();
        testUser.openSavings();
        assertNotNull(testUser.getCheckingAccount()); // Equivalence Class: an Initialized checking account
        assertNotNull(testUser.getSavingsAccount()); // Equivalence Class: an initialized savings account
        assertEquals(0, testUser.getTransactionHistory()); //Equivalence Class: No transactions at all
        testUser.getCheckingAccount().deposit(20); //tests calling functions from checking account
        assertEquals(1, testUser.getTransactionHistory()); //Equivalence Class: A single transaction from checking
        testUser.getCheckingAccount().deposit(5);
        assertEquals(2, testUser.getTransactionHistory()); //Equivalence Class: Multiple transactions from checking
        testUser.getSavingsAccount().deposit(10); //tests calling functions from savings account
        assertEquals(3, testUser.getTransactionHistory()); //Equivalence Class: Multiple transactions from checking and a single transaction from savings
        testUser.getSavingsAccount().deposit(20);;
        assertEquals(4, testUser.getTransactionHistory()); // Equivalence Class: Multiple Transactions from both checking and savings
        assertFalse(testUser.getCheckingAccount().isFrozen); // Equivalence Class: Checking Account Call
        testUser.getCheckingAccount().freeze();
        assertTrue(testUser.getCheckingAccount().isFrozen); // test the functionality of other classes calling freeze functions and it actually impacting isFrozen property
        testUser.getSavingsAccount().freeze();
        assertTrue(testUser.getSavingsAccount().isFrozen); // Equivalence Class: Savings Account Call
        //If all assertions pass, shows that a system that has the classes UserAccount, checkingAccount, and savingsAccount will be able to utilize the various functions of each other
        //while working within a single class. Everything works properly on a Macbook Pro and the assumption is this holds for all modern computers
    }
 }
