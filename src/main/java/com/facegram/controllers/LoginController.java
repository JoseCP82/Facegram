package com.facegram.controllers;

import com.facegram.App;
import com.facegram.logging.Logging;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    /**
     * Atributos de LoginController
     */
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Elementos FXML
     */
    private Button btnLogIn;
    private Button btnRegister;
    private TextField tfUser;
    private TextField tfPassword;

    /*public void changeRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../../../resources/com/facegram/register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeFeed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../../../resources/com/facegram/feed.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/

}
