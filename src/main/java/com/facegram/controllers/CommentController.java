package com.facegram.controllers;

import com.facegram.log.Log;
import com.facegram.model.DAO.CommentDAO;
import com.facegram.model.dataobject.Comment;
import com.facegram.model.dataobject.Post;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CommentController implements Initializable {

    /**
     * Atributos de la clase
     */
    private Post post;

    /**
     * Atributos bindeados con javafx
     */
    @FXML private Label lblUser;
    @FXML private TextArea txtComment;
    @FXML private Label lblDate;
    @FXML private BorderPane bdrPane;

    /**
     * Constructor con parámetro Post
     * @param post Post
     */
    private CommentController(Post post){
        this.post = post;
    }

    /**
     * Se setean los elementos del archivo fxml
     * @param userName Setea el nombre del usuario
     * @param comment Contenido del post
     * @param date Fecha de publicación del post
     */
    public void setComment(String userName, String comment, String date){
        this.lblUser.setText(userName);
        this.txtComment.setText(comment);
        this.lblDate.setText(date);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showComments();
    }

    /**
     * Lee de la bbdd todos los comentarios de un post y los muestra
     */
    private void showComments(){
        List<Comment> comments = new CommentDAO().getCommentsofPost(post);
        Pane pane = null;
        Post p = null;
        int columns = 0;
        int row = 1;
        GridPane gp = new GridPane();
        gp.setPrefSize(170,320);
        for (Comment c : comments){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("comment.fxml"));
                pane = fxmlLoader.load();
                CommentController cc = fxmlLoader.getController();
                p = c.getPost();
                p.setId(new CommentDAO().getPost().getId());
                cc.setComment(c.getUser().getName(),c.getText(),c.getDate().toString());
                if(columns==1) {
                    columns=0;
                    ++row;
                }
                gp.add(pane, ++columns, row);
            } catch (IOException e) {
                Log.warningLogging(e+"");
            }
        }
    }

    /**
     * Metodo que crea un nuevo comentario
     */
    @FXML
    private void createNewComment(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("newComment.fxml"));
            Pane pane = fxmlLoader.load();
            bdrPane.setCenter(pane); //????
        } catch (IOException e) {
            Log.warningLogging(e+"");
        }
    }
}
