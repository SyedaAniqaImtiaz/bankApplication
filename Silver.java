/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author syeda aniqa
 */
public class Silver extends Level{
    // Overview: BankAccount is mutable
    // It extends the abstract class Level and overrides the methods
    // depositMoney, withdrawmoney,
    // onlinePurchase, getBalance, and getBankAccount for the Level of customers
    // who are Silver. It also implements the setter methods and checks Level.
    //
    // The abstraction function is:
    // B.Silver <c.getBankAccount(), c.getBalance()>
    //
    //
    //
    //
    // The rep invariant is:
    // The BankAccount can't be null
    // The account.getUsername() can't be null
    //
    //
    //
    
    private BankAccount account;
    private double balance;

    // constructor
    public Silver(Level level){
        // EFFECTS: this
        this(level.getBankAccount(), level.getBalance());
    }

    // constructor
    public Silver(BankAccount account, double balance) {
        // EFFECTS: this
        super();
        this.account = account;
        this.balance = balance;
    }

    @Override
    public void depositMoney(double amount) {
        // MODIFIES: this
        // EFFECTS: Increments the balance by amount
        balance = balance + amount;
        checkLevel();
    }

    @Override
    public void withdrawMoney(double amount) throws IOException {
        // MODIFIES: this
        // EFFECTS: Checks Level and decrements the balance by amount if 
        //          balance is less than or equal to amount, otherwise
        //          writes the appropriate message to the file
        if (balance >= amount) {
            balance = balance - amount;
            checkLevel();
        }
        else {
            FileWriter file = new FileWriter("/home/student2/s2imtiaz"+ this.account.getUsername() + ".txt", true);
            file.write("Amount not available\n");
            file.flush();
            file.close();
        }
    }

    @Override
    public void onlinePurchase(double amount) throws IOException {
        // MODIFIES: this
        // EFFECTS: Checks Level, decrements the balance by (amount + 20)
        //          and displays the appropriate message if amount < 50 and
        //          (amount + 20) > balance else displays the appropriate 
        //          message
        if (amount < 50){
            FileWriter file = new FileWriter("/home/student2/s2imtiaz"+ this.account.getUsername() + ".txt", true);
            file.write("Purchase must be of 50 dollars or more\n");
            file.flush();
            file.close();
        }

        else if((amount + 20 )> balance){
            FileWriter file = new FileWriter("/home/student2/s2imtiaz"+ this.account.getUsername() + ".txt", true);
            file.write("Amount not available for purchase\n");
            file.flush();
            file.close();
        }

        else{
            FileWriter file = new FileWriter("/home/student2/s2imtiaz"+ this.account.getUsername() + ".txt", true);
            file.write("A $20 additional fee will apply\n");
            file.flush();
            file.close();
            balance = balance - amount - 20;
            checkLevel();
        }
    }

    @Override
    public BankAccount getBankAccount() {
        // EFFECTS: Returns account
        return account;
    }

    public void setBankAccount(BankAccount account) {
        // REQUIRES: account != null
        // EFFECTS: Sets this.account to account
        this.account = account;
    }

    @Override
    public double getBalance() {
        // EFFECTS: Returns balance
        return balance;
    }

    public void setBalance(double balance) {
        // EFFECTS: Sets this.balance to balance        
        this.balance = balance;
    }

    public void checkLevel() {
        // MODIFIES: Level
        // EFFECTS: Sets Level for account        
        if ((balance >= 1000) && (balance < 2000)){
            account.setLevel(new Gold(this));
        }

        else if (balance >= 2000){
            account.setLevel(new Platinum(this));
        }
    }
    
    public boolean repOK() {         
        // EFFECTS: Returns true if the rep invariant holds for this            
        // object; otherwise returns false    
        // c) Write the code for the repOK() here 
        if ((account == null) || (account.getUsername() == null)){
            return false;
        }
        
        return true;
        
    }     

    @Override
    public String toString() {         
        // EFFECTS: Returns a string that contains the strings in the          
        //          BankAccount and balance. Implements the          
        //          abstraction function.   
        // d) Write the code for the toString() here
        return ("The account is" + account + "with balance" + balance);
    }    
}

