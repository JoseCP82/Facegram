package com.facegram.controllers;

import com.facegram.logging.Logging;
import com.facegram.model.DAO.PostDAO;
import com.facegram.model.dataobject.Post;
import com.facegram.utils.chronometer.Chronometer;
import com.facegram.utils.message.ConfirmMessage;
import com.facegram.utils.message.InfoMessage;
import com.facegram.utils.message.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PostController implements Initializable {

    /**
     * Atributos de clase bindeados con Scene Builder
     */
    @FXML private Button btnClose;
    @FXML private AnchorPane anchorBody;

    /**
     * Atributos de clase
     */
    private Chronometer chronometer;
    private PostDAO pDAO;
    private Stage stage;

    /**
     * Inicializa los elementos del controlador
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chronometer = new Chronometer();
        chronometer.start();

        //List<Post> posts = new PostDAO().getAll();
        //txtPost.setText(posts.toString());

    }

    /**
     * Método que crea un nuevo post
     */
    @FXML
    private void createPost() {
        loadFXMLChildren("src/main/resources/com/facegram/fxml/showPosts.fxml");

    }

    /**
     * Carga en el fxml principal un hijo (otro fxml)
     * @param fxml Nombre del fxml a cargar
     */
    private void loadFXMLChildren(String fxml) {
        AnchorPane anchor=null;
        try {
            anchor = FXMLLoader.load(getClass().getResource(fxml));
            anchorBody.getChildren().setAll(anchor);

        } catch (IOException e) {
            System.out.println("Es null");
            Logging.warningLogging(e+"");
        }
    }

    /**
     * Minimiza la aplicación a la barra de tareas
     */
    @FXML
    private void minimizeWindow() {
        this.stage = (Stage) this.btnClose.getScene().getWindow();
        this.stage.setIconified(true);
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
            new InfoMessage("Duración de la sesión:\n"+this.chronometer.getSessionTime()).showMessage();
            Logging.infoLogging("Aplicación finalizada.");
            this.stage = (Stage) this.btnClose.getScene().getWindow();
            this.stage.close();
        }
    }
}
