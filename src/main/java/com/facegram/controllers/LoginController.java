package com.facegram.controllers;

import com.facegram.App;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    private Button btnLogIn;
    private Button btnRegister;
    private TextField tfUser;
    private TextField tfPassword;

    public void changeRegister(){
        btnRegister.setOnMouseClicked(e->{
            App.class.getResource("register.fxml");
        });
    }

}
