/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author syeda aniqa
 */
import java.io.IOException;

public abstract class Level {
    abstract void depositMoney(double amount);

    abstract void withdrawMoney(double amount) throws IOException;

    abstract void onlinePurchase(double amount) throws IOException;

    abstract BankAccount getBankAccount();

    abstract double getBalance();

}

