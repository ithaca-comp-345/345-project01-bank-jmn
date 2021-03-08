package edu.ithaca.dragon.bank;

import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        CentralBank bank = new CentralBank();
        BankTeller teller = new BankTeller(bank);
        ATM atm = new ATM(bank);
        Admin admin = new Admin(bank);
        int id = 10000;
        String input = " ";
        while (!input.equalsIgnoreCase("quit")) {
            Scanner scnr = new Scanner(System.in);
            System.out.println("Welcome to the Bank Demo Enter what user you are (teller, atm user, admin): ");
            input = scnr.nextLine();

            // Teller actions
            if (input.equalsIgnoreCase("teller")) {
                String innerInput = " ";
                while (!innerInput.equalsIgnoreCase("back")) {
                    System.out.println("Hello Bank Teller, enter the number of the action you would like to take: ");
                    System.out.println("1. Create new account");
                    System.out.println("2. Open a new savings or checking account");
                    innerInput = scnr.nextLine();

                    // Create account
                    if (innerInput.equalsIgnoreCase("1")) {
                        String pinInput;
                        System.out.println("Have the customer enter the pin number they want to have: ");
                        pinInput = scnr.nextLine();
                        System.out.println(
                                "The customers id is " + id + " tell them to write that down and not to lose it");
                        teller.addUserAccount(pinInput, id);
                        id++;
                    }
                    // Open checking or savings
                    else if (innerInput.equalsIgnoreCase("2")) {
                        int customerId;
                        System.out.println("Enter customers id: ");
                        customerId = scnr.nextInt();

                        String pinInput;
                        System.out.println("Have the customer enter their pin: ");
                        pinInput = scnr.nextLine();
                        pinInput = scnr.nextLine();

                        // Try to log in
                        if (teller.confirmCredentials(customerId, pinInput)) {
                            System.out.println("Log in successful");
                            String accountType = "";
                            System.out.println("What account does the customer want checking or savings: ");
                            accountType = scnr.nextLine();
                            if (accountType.contains("checking")) {
                                teller.addCheckingAccount();
                                System.out.println("Checking account added");
                            } else if (accountType.contains("savings")) {
                                teller.addSavingsAccount();
                                System.out.println("Savings account added");
                            } else {
                                System.out.println("Invalid account type");
                            }
                            // log off account
                            teller.close();
                        } else {
                            System.out.println("Login unsuccessful");
                        }
                    } else {
                        System.out.println("Invalid action number ");
                    }
                    System.out.println("Enter 'back' to go back to user select, press enter to continue: ");
                    innerInput = scnr.nextLine();
                }
            }
            // ATM USER
            else if (input.contains("atm user") || input.equalsIgnoreCase("atm user")) {

                String innerInput = " ";
                while (!innerInput.equalsIgnoreCase("back")) {
                    // LOGIN
                    System.out.println("Hello ATM Customer");
                    int customerId;
                    System.out.println("Enter your account id: ");
                    customerId = scnr.nextInt();

                    String pinInput;
                    System.out.println("Enter your pin: ");
                    pinInput = scnr.nextLine();
                    pinInput = scnr.nextLine();

                    String continueTransactions = "";
                    if (atm.confirmCredentials(customerId, pinInput)) {
                        System.out.println("Log in successful");
                        while (!continueTransactions.equalsIgnoreCase("end")) {
                            System.out.println("Enter the number of the action you would like to take: ");
                            System.out.println("1. Withdraw");
                            System.out.println("2. Deposit");
                            System.out.println("3. Get Balance");
                            continueTransactions = scnr.nextLine();
                            
                            //Withdraw
                            if (continueTransactions.equalsIgnoreCase("1")) {
                                String accountType;
                                System.out.println("Enter the account to Withdraw from (checking or savings): ");
                                accountType = scnr.nextLine();
                                double amt;
                                System.out.println("Enter the amount to Withdraw: ");
                                amt = scnr.nextDouble();
                                scnr.nextLine();

                                if (accountType.contains("checking")) {
                                    try {
                                        atm.withdraw(amt, "checking");
                                    } catch (InsufficientFundsException e) {
                                        System.out.println("Not enough money to withdraw");
                                    }
                                } else if (accountType.contains("savings")) {
                                    try {
                                        atm.withdraw(amt, "savings");
                                    } catch (InsufficientFundsException e) {
                                        System.out.println("Not enough money to withdraw");
                                    }
                                }
                                else{
                                    System.out.println("Invalid account type");
                                }
                            }
                            //Depost
                            else if (continueTransactions.equalsIgnoreCase("2")){
                                String accountType;
                                System.out.println("Enter the account to Deposit to (checking or savings): ");
                                accountType = scnr.nextLine();
                                double amt;
                                System.out.println("Enter the amount to Deposit: ");
                                amt = scnr.nextDouble();
                                scnr.nextLine();

                                if (accountType.contains("checking")) {
                                    atm.deposit(amt, "checking");
                                }
                                else if (accountType.contains("savings")) {
                                    atm.deposit(amt, "savings");
                                }
                                else{
                                    System.out.println("Invalid account type");
                                }
                            }
                            //Get Balance
                            else if (continueTransactions.equalsIgnoreCase("3")){
                                String accountType;
                                System.out.println("Enter the account you want to check the balance of (checking or savings): ");
                                accountType = scnr.nextLine();
                            
                                if (accountType.contains("checking")) {
                                    System.out.println("$" + atm.getBalance("checking"));
                                }
                                else if (accountType.contains("savings")) {
                                    System.out.println("$" +atm.getBalance("savings"));
                                }
                                else{
                                    System.out.println("Invalid account type");
                                }
                            }
                            //Invalid input
                            else{
                                System.out.println("Invalid action");
                            }
                            System.out.println("Enter 'end' to log out of the atm, press enter to continue: ");
                            continueTransactions = scnr.nextLine();
                        }
                        //log out of atm
                        atm.close();
                    }
                    else{
                        System.out.println("Log unsuccessful");
                    }
                    
                    System.out.println("Enter 'back' to go back to user select, press enter to continue: ");
                    innerInput = scnr.nextLine();
                }
            }
            //Admin
            else if(input.equalsIgnoreCase("admin")){
                String innerInput = "";
                while (!innerInput.equalsIgnoreCase("back")) {
                    //Chose Action
                    System.out.println("Hello Admin user");
                    System.out.println("Enter the number of the action you would like to take");
                    System.out.println("1. Freeze Account");
                    System.out.println("2. Unfreeze Account");
                    innerInput = scnr.nextLine();
                    //Freeze Account
                    if (innerInput.equalsIgnoreCase("1")){
                        int inputId;
                        System.out.println("Enter the account id");
                        inputId = scnr.nextInt();
                        scnr.nextLine();
                        admin.freezeAccount(inputId);
                    }
                    //Unfreeze account
                    else if (innerInput.equalsIgnoreCase("2")){
                        int inputId;
                        System.out.println("Enter the account id");
                        inputId = scnr.nextInt();
                        scnr.nextLine();
                        admin.unfreezeAccount(inputId);
                    }
                    //Invalid action
                    else{
                        System.out.println("Invalid action input");
                    }

                    System.out.println("Enter 'back' to go back to user select, press enter to continue: ");
                    innerInput = scnr.nextLine();

                }
            }
            else{
                System.out.println("Invalid user input");
            }
            System.out.println("Enter 'quit' to end demo, press enter to continue: ");
            input = scnr.nextLine();
        }
        
        
    }
}
