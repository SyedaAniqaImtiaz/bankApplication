/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.LineNumberReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import javafx.scene.text.Text;

/**
 *
 * @author syeda aniqa
 */
public class MainPage implements Initializable {
    @FXML
    private Text note1;
    
    @FXML
    private Text note2;    
    
    @FXML
    private Label label;
    
    @FXML
    private Button managerLogin;
    
    @FXML
    private Button customerLogin;
    
    @FXML
    private Button depositMoney;
    
    @FXML
    private Button withdrawMoney;
    
    @FXML
    private Button makePurchase;
       
    @FXML
    private Button getBalance;    
    
    @FXML
    private Button logout;
    
    @FXML
    private TextField username;
    
    @FXML
    private TextField password;
    
    @FXML
    private TextField amount;
    
    @FXML
    private AnchorPane rootPane1;
    
    @FXML
    private void onClick_btn_managerLogin(ActionEvent event) throws IOException {
        Manager manager = new Manager();
        manager.setRole();
        manager.setUsername();
        manager.setPassword();
        if(manager.getRole().equals("manager")){
            if(username.getText().equals(manager.getUsername()) && password.getText().equals(manager.getPassword())){
                label.setText("Login successful!");          
                AnchorPane rootPane = FXMLLoader.load(getClass().getResource("ManagerPage.fxml"));
                rootPane1.getChildren().setAll(rootPane);
            }

            else{
                username.clear();
                password.clear();
                label.setText("Login failed!");
            }
        }
    }
    
    @FXML
    private void onClick_btn_customerLogin(ActionEvent event) throws IOException {
        String[] authentication = new String[3];
        String temp;
        if(new File("/home/student2/s2imtiaz"+ username.getText() + ".txt").exists()){
            LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream("/home/student2/s2imtiaz"+ username.getText() + ".txt"), "UTF-8"));
            while (((temp = reader.readLine()) != null) && reader.getLineNumber() <= 3){
                    authentication[reader.getLineNumber() - 1] = temp;
                 }
            if(authentication[0].equals("customer")){
                if((username.getText().equals(authentication[1])) && (password.getText().equals(authentication[2]))){
                    label.setText("Login successful!");
                    username.setVisible(false);
                    password.setVisible(false);
                    managerLogin.setVisible(false);
                    customerLogin.setVisible(false);
                    withdrawMoney.setVisible(true);
                    depositMoney.setVisible(true);
                    makePurchase.setVisible(true);
                    getBalance.setVisible(true);
                    logout.setVisible(true);
                    amount.setVisible(true); 
                    note1.setVisible(false);
                    note2.setVisible(false);
                }

                else{
                    username.clear();
                    password.clear();
                    label.setText("Login failed!");
                }
            }
        }
        else{
            username.clear();
            password.clear();
            label.setText("Login failed!");
        }
    }
    
    @FXML
    private void onClick_btn_logout(ActionEvent event) throws IOException {
        username.clear();
        password.clear();
        username.setVisible(true);
        password.setVisible(true);
        managerLogin.setVisible(true);
        customerLogin.setVisible(true);        
        withdrawMoney.setVisible(false);
        depositMoney.setVisible(false);
        makePurchase.setVisible(false);
        getBalance.setVisible(false);
        logout.setVisible(false);
        amount.setVisible(false);
        note1.setVisible(true);
        note2.setVisible(true);
        label.setText("");
    }
    
    @FXML
    private void onClick_btn_makePurchase(ActionEvent event) throws IOException {   
        BankAccount a = new BankAccount(username.getText(),password.getText());
        String levelString= "Silver";
        String temp;
        String balanceString = "0";
        Double balance = 0.0;
        int numOfLines=0;
        
        LineNumberReader reader1 = new LineNumberReader(new InputStreamReader(new FileInputStream("/home/student2/s2imtiaz"+ username.getText() + ".txt"), "UTF-8"));
        while (((temp = reader1.readLine()) != null)){
                numOfLines =reader1.getLineNumber() ;
        }
             
        LineNumberReader reader2 = new LineNumberReader(new InputStreamReader(new FileInputStream("/home/student2/s2imtiaz"+ username.getText() + ".txt"), "UTF-8"));
        while (((temp = reader2.readLine()) != null) && reader2.getLineNumber() <= (numOfLines - 2)){
                levelString = temp;
        }
             
        LineNumberReader reader3 = new LineNumberReader(new InputStreamReader(new FileInputStream("/home/student2/s2imtiaz"+ username.getText() + ".txt"), "UTF-8"));
        while (((temp = reader3.readLine()) != null)){
                balanceString = temp;
        }             
           
        balance = Double.parseDouble(balanceString);
             
        if(levelString.equals("Silver")){
            Level l = new Silver(a,balance);
            a.setLevel(l);
        }

        else if(levelString.equals("Gold")){
            Level l = new Gold(a,balance);
            a.setLevel(l);
        }

        if(levelString.equals("Platinum")){
            Level l = new Platinum(a,balance);
            a.setLevel(l);
        }
        
        a.onlinePurchase(Double.parseDouble(amount.getText()));
        label.setText("Transaction processed! Refer to file for details.");
        amount.clear();
    }
    
    @FXML
    private void onClick_btn_depositMoney(ActionEvent event) throws IOException {
        BankAccount a = new BankAccount(username.getText(),password.getText());
        String levelString= "Silver";
        String temp;
        String balanceString = "0";
        Double balance = 0.0;
        int numOfLines=0;
        
        LineNumberReader reader1 = new LineNumberReader(new InputStreamReader(new FileInputStream("/home/student2/s2imtiaz"+ username.getText() + ".txt"), "UTF-8"));
        while (((temp = reader1.readLine()) != null)){
                numOfLines =reader1.getLineNumber() ;
        }
             
        LineNumberReader reader2 = new LineNumberReader(new InputStreamReader(new FileInputStream("/home/student2/s2imtiaz"+ username.getText() + ".txt"), "UTF-8"));
        while (((temp = reader2.readLine()) != null) && reader2.getLineNumber() <= (numOfLines - 2)){
                levelString = temp;
        }
             
        LineNumberReader reader3 = new LineNumberReader(new InputStreamReader(new FileInputStream("/home/student2/s2imtiaz"+ username.getText() + ".txt"), "UTF-8"));
        while (((temp = reader3.readLine()) != null)){
                balanceString = temp;
        }             
           
        balance = Double.parseDouble(balanceString);
             
        if(levelString.equals("Silver")){
            Level l = new Silver(a,balance);
            a.setLevel(l);
        }

        else if(levelString.equals("Gold")){
            Level l = new Gold(a,balance);
            a.setLevel(l);
        }

        if(levelString.equals("Platinum")){
            Level l = new Platinum(a,balance);
            a.setLevel(l);
        }
        
        a.depositMoney(Double.parseDouble(amount.getText()));
        label.setText("Transaction processed! Refer to file for details.");        
        amount.clear();
    }
    
    @FXML
    private void onClick_btn_withdrawMoney(ActionEvent event) throws IOException {
        BankAccount a = new BankAccount(username.getText(),password.getText());
        String levelString= "Silver";
        String temp;
        String balanceString = "0";
        Double balance = 0.0;
        int numOfLines=0;
        
        LineNumberReader reader1 = new LineNumberReader(new InputStreamReader(new FileInputStream("/home/student2/s2imtiaz"+ username.getText() + ".txt"), "UTF-8"));
        while (((temp = reader1.readLine()) != null)){
                numOfLines =reader1.getLineNumber() ;
        }
             
        LineNumberReader reader2 = new LineNumberReader(new InputStreamReader(new FileInputStream("/home/student2/s2imtiaz"+ username.getText() + ".txt"), "UTF-8"));
        while (((temp = reader2.readLine()) != null) && reader2.getLineNumber() <= (numOfLines - 2)){
                levelString = temp;
        }
             
        LineNumberReader reader3 = new LineNumberReader(new InputStreamReader(new FileInputStream("/home/student2/s2imtiaz"+ username.getText() + ".txt"), "UTF-8"));
        while (((temp = reader3.readLine()) != null)){
                balanceString = temp;
        }             
           
        balance = Double.parseDouble(balanceString);
             
        if(levelString.equals("Silver")){
            Level l = new Silver(a,balance);
            a.setLevel(l);
        }

        else if(levelString.equals("Gold")){
            Level l = new Gold(a,balance);
            a.setLevel(l);
        }

        if(levelString.equals("Platinum")){
            Level l = new Platinum(a,balance);
            a.setLevel(l);
        }
        
        a.withdrawMoney(Double.parseDouble(amount.getText()));
        label.setText("Transaction processed! Refer to file for details.");
        amount.clear();
    }
    
    @FXML
    private void onClick_btn_getBalance(ActionEvent event) throws IOException {
        String temp;
        String balance = "0";
             
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream("/home/student2/s2imtiaz"+ username.getText() + ".txt"), "UTF-8"));
        while (((temp = reader.readLine()) != null)){
                balance = temp;
        }             
        
        label.setText("The current balance is $" + balance +"! Refer to file for details.");
        amount.clear();       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        withdrawMoney.setVisible(false);
        depositMoney.setVisible(false);
        makePurchase.setVisible(false);
        getBalance.setVisible(false);
        logout.setVisible(false);
        amount.setVisible(false);
    }    
    
}
