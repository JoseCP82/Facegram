package com.facegram.controllers;

import com.facegram.log.Log;
import com.facegram.model.DAO.PostDAO;
import com.facegram.model.dataobject.Post;
import com.facegram.utils.message.ConfirmMessage;
import com.facegram.utils.message.InfoMessage;
import com.facegram.utils.message.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import java.io.IOException;

public class PostController extends Controller {

    /**
     * Atributos bindeados con javafx
     */
    @FXML private Label lblUser;
    @FXML private Label lblDate;
    @FXML private TextArea txtContent;
    @FXML private Button btnLike;
    @FXML private Button btnDelete;

    /**
     * Atributos de clase
     */
    private boolean btnLikePush = false;
    private int idPost;

    @FXML
    public void deletePost() {
        ConfirmMessage cm = new ConfirmMessage("¿Seguro que desa eliminar el post actual?");
        cm.showMessage();
        if(cm.getBt()== ButtonType.OK){
            new PostDAO(idPost).delete();
            new InfoMessage("Post eliminado").showMessage();
        }
    }

    //public void updatePOst() {}


    /**
     * Setea los elementos del archivo fxml
     * @param post Post a setear
     * @param byeStatus Estado del boton Bye
     */
    public void setPost(Post post, boolean byeStatus) {
        this.lblUser.setText(post.getOwner().getName());
        this.txtContent.setText(post.getText());
        this.lblDate.setText(post.getDate().toString());
        this.btnDelete.setDisable(byeStatus);
        this.idPost=post.getId();
    }

    /**
     * Comprueba si se ha pulsado el botón de like
     */
    @FXML
    private void likeStatus() {
        if(!this.btnLikePush) {
            this.btnLike.setStyle("-fx-background-color: red");
            this.btnLike.setTextFill(Color.WHITE);
            this.btnLikePush=true;

        } else {
            this.btnLike.setStyle("-fx-background-color: lightgrey");
            this.btnLike.setTextFill(Color.BLACK);
            this.btnLikePush=false;
        }
    }

    /**
     * Carga el archivo fxml donde se mostrarán los comentarios creados
     * así como el boton para crear un nuevo comentario
     */
    @FXML
    private void showComments() {
        try {
            BorderPane bdrPane = borderPane;
            bdrPane.getChildren().remove(bdrPane.getCenter());
            AnchorPane pane = FXMLLoader.load(App.class.getResource("showComments.fxml"));
            bdrPane.setCenter(pane);
        } catch (IOException e) {
            Log.warningLogging(e+"");
        }
    }
}
