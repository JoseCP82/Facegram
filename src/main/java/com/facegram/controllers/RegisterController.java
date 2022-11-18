package com.facegram.controllers;

import com.facegram.model.DAO.UserDAO;
import com.facegram.model.dataobject.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML private Button btnRegister;
    @FXML private Button btnCancel;
    @FXML private TextField tfName;
    @FXML private TextField tfPassword;

    @FXML public void register(){
        if(!tfName.getText().equals("") && !tfPassword.getText().equals("")){
                String name = tfName.getText();
                String password = tfPassword.getText();
                User u = new User(name,password);
                UserDAO uDAO = new UserDAO(u);
                uDAO.insert();
        }
    }
}
