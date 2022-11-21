package com.facegram.controllers;

import com.facegram.App;
import com.facegram.log.Log;
import com.facegram.utils.message.ConfirmMessage;
import com.facegram.utils.message.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginController {

    /**
     * Atributos de LoginController
     */
    private Stage stage;

    /**
     * Elementos FXML
     */
    @FXML private Button btnLogIn;
    @FXML private Button btnRegister;
    @FXML private Button btnClose;
    @FXML private Button btnMinimize;
    @FXML private TextField tfName;
    @FXML private TextField tfPassword;

    @FXML public void changeRegister() throws IOException {
        this.stage = (Stage) this.btnClose.getScene().getWindow();
        this.stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 480);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML public void changeFeed() throws IOException {
        this.stage = (Stage) this.btnClose.getScene().getWindow();
        this.stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("feed.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 480);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    /**
     * Minimiza la aplicación a la barra de tareas
     */
    @FXML private void minimizeWindow() {
        this.stage = (Stage) this.btnClose.getScene().getWindow();
        this.stage.setIconified(true);
    }

    /**
     * Finaliza la aplicación
     */
    @FXML private void closeApp() {
        Message ms = new ConfirmMessage("¿Seguro que desea salir?");
        ms.showMessage();
        if(((ConfirmMessage) ms).getBt() == ButtonType.OK) {
            //this.chronometer.interrupt();
            //new InfoMessage("Duración de la sesión:\n"+this.chronometer.getSessionTime()).showMessage();
            Log.infoLogging("Aplicación finalizada.");
            this.stage = (Stage) this.btnClose.getScene().getWindow();
            this.stage.close();
        }
    }
}
