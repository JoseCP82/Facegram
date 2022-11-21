package com.facegram.controllers;

import com.facegram.App;
import com.facegram.log.Log;
import com.facegram.model.DAO.PostDAO;
import com.facegram.model.DAO.UserDAO;
import com.facegram.model.dataobject.Post;
import com.facegram.model.dataobject.User;
import com.facegram.utils.chronometer.Chronometer;
import com.facegram.utils.message.ConfirmMessage;
import com.facegram.utils.message.InfoMessage;
import com.facegram.utils.message.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FeedController implements  Initializable {

    /**
     * Atributos de clase bindeados con Scene Builder
     */
    @FXML private Button btnClose;
    @FXML private BorderPane bdrPane;
    @FXML private AnchorPane anchorBody;

    /**
     * Atributos de clase
     */
    private User user;
    private Chronometer chronometer;
    private Stage stage;

    /**
     * Constructor con parametro User
     * @param user User
     */
    public FeedController(User user) {
        this.user = user;
        System.out.println(this.user);
    }

    /**
     * Constructor por defecto
     */
    public FeedController(){}

    /**
     * Inicializa los elementos del controlador
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //chronometer = new Chronometer();
        //chronometer.start();
        showPosts();

        chronometer = new Chronometer();
        chronometer.start();
        anchorNewPost.setVisible(false);
        showPosts();


    }

    /**
     * Lee de la bbdd todos los post y los muestra
     */
    private void showPosts()  {
        List<Post> posts = new PostDAO().getAll();
        Pane pane = null;
        User u = null;
        int columns = 0;
        int row = 1;
        GridPane gp = new GridPane();
        gp.setPrefSize(170,320);
        gp.setGridLinesVisible(true);
        for(Post p : posts) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("post.fxml"));
                pane = fxmlLoader.load();
                PostController pc = fxmlLoader.getController();
                u = p.getOwner();
                u.setName(new UserDAO().get(u.getId()).getName());
                pc.setPost(p.getOwner().getName(),p.getText(),p.getDate().toString());
                if(columns==1) {
                    columns=0;
                    ++row;
                }
                gp.add(pane, ++columns, row);
            } catch (IOException e) {
                Log.warningLogging(e+"");
            }
        }
        anchorBody.getChildren().add(gp);
    }

    /**
     * Método que crea un nuevo post
     */
    @FXML
    private void createNewPost() {
        System.out.println(this.user);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("newPost.fxml"));
            Pane pane = fxmlLoader.load();
            bdrPane.setCenter(pane);
        } catch (IOException e) {
            Log.warningLogging(e+"");
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
            Log.infoLogging("Aplicación finalizada.");

            this.chronometer.interrupt();
            new InfoMessage("Duración de la sesión:\n"+this.chronometer.getSessionTime()).showMessage();
            Logging.infoLogging("Aplicación finalizada.");

            this.stage = (Stage) this.btnClose.getScene().getWindow();
            this.stage.close();
        }
    }
}
