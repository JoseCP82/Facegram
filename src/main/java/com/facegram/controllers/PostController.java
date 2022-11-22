package com.facegram.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

public class PostController {

    /**
     * Atributos bindeados con javafx
     */
    @FXML private Label lblUser;
    @FXML private Label lblDate;
    @FXML private TextArea txtContent;
    @FXML private Button btnLike;

    private boolean btnLikePush = false;

    /**
     * Setea los elementos del archivo fxml
     * @param userName Setea el nombre del usuario
     * @param content Contenido del post
     * @param date Fecha de publicación del post
     */
    public void setPost(String userName, String content, String date) {
        this.lblUser.setText(userName);
        this.txtContent.setText(content);
        this.lblDate.setText(date);
    }

    /**
     * Comprueba si se ha pulsado el botón de like
     */
    @FXML
    public void likeStatus() {
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

}
