package com.facegram.controllers;

import com.facegram.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private Button btnLogIn;
    private Button btnRegister;
    private Button btnFeed;
    private TextField tfUser;
    private TextField tfPassword;

    public void changeRegister(Stage stage) {
        btnRegister.setOnMouseClicked(e->{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("register.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 320, 240);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        });
    }

    public void changeFeed(Stage stage){
        btnFeed.setOnMouseClicked(e->{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("feed.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 320, 240);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        });
    }

}
