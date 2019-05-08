/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

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

/**
 * FXML Controller class
 *
 * @author aniqa
 */
public class ManagerPage implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label label;
    
    @FXML
    private Button addCustomer;
    
    @FXML
    private Button removeCustomer;
    
    @FXML
    private TextField name;
    
    @FXML
    private TextField pass;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private void onClick_btn_addCustomer(ActionEvent event) throws IOException {
        Manager manager = new Manager();
        manager.addCustomer(name.getText(), pass.getText());
        label.setText("Customer added successfully!");
        name.clear();
        pass.clear();
    }
    
    @FXML
    private void onClick_btn_removeCustomer(ActionEvent event) throws IOException {
        Manager manager = new Manager();
        manager.removeCustomer(name.getText(), pass.getText());
        label.setText("Customer removed successfully!");
        name.clear();
        pass.clear();
    }
    
    @FXML
    private void onClick_btn_logout(ActionEvent event) throws IOException {
        AnchorPane pane2 = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        rootPane.getChildren().setAll(pane2);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
