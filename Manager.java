/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.*;

/**
 *
 * @author syeda aniqa
 */
public class Manager {
    private String role, username, password;
    private final ArrayList<String> accounts = new ArrayList<>();
    
     public String getRole() {
        return role;
    }

    public void setRole() {
        role = "manager";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername() {
        username = "admin";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        password = "admin";
    }

    public void addCustomer(String name, String pass) throws IOException {
        accounts.add(name);
        FileWriter file = new FileWriter("/home/student2/s2imtiaz"+ name + ".txt", true);
        file.write("customer\n");
        file.write(name + "\n");
        file.write(pass + "\n");
        file.write("Level:\nSilver\n");
        file.write("Balance:\n100.0\n"); 
        file.flush();
        file.close();
        BankAccount a = new BankAccount(name,pass);
    }

    public void removeCustomer(String name, String pass) throws IOException {
        accounts.remove(name);
        Files.deleteIfExists(Paths.get("/home/student2/s2imtiaz"+name+".txt"));
    }
}


