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
public class BankAccount {
    private Level level;
    private String password;
    private String username;
    private String role;

    public BankAccount( String username, String password) throws IOException {
        super();
        this.level = new Silver(this, 100);
        this.role = "customer";
        this.username = username;
        this.password = password;
    }

    public void depositMoney(double amount) throws IOException {
        level.depositMoney(amount);
        FileWriter file = new FileWriter("/home/student2/s2imtiaz"+ this.username + ".txt", true);
        file.write("Deposited:\n"+ amount + "\n");
        file.write("Level:\n"+ this.level.getClass().getSimpleName() + "\n");
        file.write("Balance:\n"+ this.level.getBalance() + "\n");        
        file.flush();
        file.close();
    }
    public void withdrawMoney(double amount) throws IOException {
        level.withdrawMoney(amount);
        FileWriter file = new FileWriter("/home/student2/s2imtiaz"+ this.username + ".txt", true);
        file.write("Withdrew:\n"+ amount + "\n");
        file.write("Level:\n"+ this.level.getClass().getSimpleName() + "\n");
        file.write("Balance:\n"+ this.level.getBalance() + "\n");
        file.flush();
        file.close();
    }
    public void onlinePurchase(double amount) throws IOException {
        level.onlinePurchase(amount);
        FileWriter file = new FileWriter("/home/student2/s2imtiaz"+ this.username + ".txt", true);
        file.write("Purchase Amount:\n"+ amount + "\n");
        file.write("Level:\n"+ this.level.getClass().getSimpleName() + "\n");
        file.write("Balance:\n"+ this.level.getBalance() + "\n");
        file.flush();
        file.close();
    }
    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

