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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FeedController implements Initializable {

    /**
     * Atributos de clase bindeados con Scene Builder
     */
    @FXML private Button btnClose;
    @FXML private AnchorPane anchorBody;
    @FXML private VBox vBoxPosts;
    @FXML private AnchorPane anchorNewPost;

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
        //chronometer = new Chronometer();
        //chronometer.start();
        anchorNewPost.setVisible(false);
        //showPosts();

    }

    /**
     * Lee de la bbdd todos los post y los muestra
     */
    private void showPosts() {
        vBoxPosts.setVisible(true);
        List<Post> posts = new PostDAO().getAll();
        PostController pc = new PostController();
        for (Post p : posts) {
            try {
                AnchorPane ap = FXMLLoader.load(getClass().getResource("post.fxml"));
                pc.setPost(p.getOwner().getName(), p.getText(), p.getDate().toString());
                vBoxPosts.getChildren().add(ap);
            } catch (IOException e) {
                Logging.warningLogging(e+"");
            }
        }
    }

    /**
     * Método que crea un nuevo post
     */
    @FXML
    private void createPost() {
        vBoxPosts.setVisible(false);
        anchorNewPost.setVisible(true);
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
            //this.chronometer.interrupt();
            //new InfoMessage("Duración de la sesión:\n"+this.chronometer.getSessionTime()).showMessage();
            Logging.infoLogging("Aplicación finalizada.");
            this.stage = (Stage) this.btnClose.getScene().getWindow();
            this.stage.close();
        }
    }
}
