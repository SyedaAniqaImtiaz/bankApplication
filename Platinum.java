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
public class Platinum extends Level {
    private BankAccount account;
    private double balance;

    public Platinum(Level level){
        this(level.getBankAccount(), level.getBalance());
    }

    public Platinum(BankAccount account, double balance) {
        super();
        this.account = account;
        this.balance = balance;
    }

    @Override
    public void depositMoney(double amount) {
        balance = balance + amount;
        checkLevel();
    }

    @Override
    public void withdrawMoney(double amount) throws IOException {
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

    public void checkLevel() {
        if (balance < 0){
            account.setLevel(new Silver(this));
        }else if (balance < 2000){
            account.setLevel(new Gold(this));
        }
    }

    @Override
    public void onlinePurchase(double amount) throws IOException {
        if (amount < 50){
            FileWriter file = new FileWriter("/home/student2/s2imtiaz"+ this.account.getUsername() + ".txt", true);
            file.write("Purchase must be of 50 dollars or more\n");
            file.flush();
            file.close();
        }

        else if(amount > balance){
            FileWriter file = new FileWriter("/home/student2/s2imtiaz"+ this.account.getUsername() + ".txt", true);
            file.write("Amount not available for purchase\n");
            file.flush();
            file.close();
        }

        else{
            FileWriter file = new FileWriter("/home/student2/s2imtiaz"+ this.account.getUsername() + ".txt", true);
            file.write("No additional fee will apply\n");
            file.flush();
            file.close();
            balance = balance - amount;
            checkLevel();
        }
    }

    @Override
    public BankAccount getBankAccount() {
        return this.account;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    public void setBankAccount(BankAccount account) {
        this.account = account;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}


