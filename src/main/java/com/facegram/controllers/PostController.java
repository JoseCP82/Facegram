package com.facegram.controllers;

import com.facegram.logging.Logging;
import com.facegram.model.DAO.PostDAO;
import com.facegram.model.dataobject.Post;
import com.facegram.utils.chronometer.Chronometer;
import com.facegram.utils.message.ConfirmMessage;
import com.facegram.utils.message.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    @FXML private Button btnCreate;
    @FXML private Button btnClose;
    @FXML private TextArea txtAreaPost1;
    @FXML AnchorPane anchorBody;

    /**
     * Atributos de clase
     */
    private Chronometer chronometer;
    private PostDAO pDAO;

    /**
     * Inicializa los elementos del controlador
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chronometer = new Chronometer();
        chronometer.start();

        List<Post> posts = new PostDAO().getAll();
        this.txtAreaPost1.setText(posts.toString());
    }

    /**
     * Método que crea un nuevo post
     */
    @FXML
    public void createPost() throws IOException {
        loadFXMLChildren("newPost.fxml");

    }

    /**
     * Carga en el fxml principal un hijo (otro fxml)
     * @param fxml Nombre del fxml a cargar
     */
    private void loadFXMLChildren(String fxml) throws IOException {
        AnchorPane anchor = FXMLLoader.load(getClass().getResource(fxml));
        this.anchorBody.getChildren().setAll(anchor);
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
