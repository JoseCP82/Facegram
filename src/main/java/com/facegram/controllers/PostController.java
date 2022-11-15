package com.facegram.controllers;

import com.facegram.logging.Logging;
import com.facegram.model.DAO.PostDAO;
import com.facegram.utils.chronometer.Chronometer;
import com.facegram.utils.message.ConfirmMessage;
import com.facegram.utils.message.Message;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PostController implements Initializable {

    /**
     * Atributos de clase bindeados con Scene Builder
     */
    @FXML private Button btnCreate;
    @FXML private Button btnClose;

    /**
     * Atributos de clase
     */
    private Chronometer chronometer;
    private PostDAO pDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chronometer = new Chronometer();
        chronometer.start();
        pDAO = new PostDAO();
        pDAO.getAll();
    }

    /**
     * Método que crea un nuevo post
     */
    @FXML
    public void createPost(){

    }

    /**
     * Finaliza la aplicación
     */
    @FXML
    private void closeApp() {
        Message ms = new ConfirmMessage("¿Seguro que desea salir?");
        ms.showMessage();
        if(((ConfirmMessage) ms).getBt() == ButtonType.OK) {
            this.chronometer.interrupt();
            Logging.infoLogging("Aplicación finalizada.");
            Stage stage = (Stage) this.btnClose.getScene().getWindow();
            stage.close();
        }
    }
}
