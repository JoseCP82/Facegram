package com.facegram.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PostController {

    /**
     * Atributos bindeados con javafx
     */
    @FXML private Label lblUser;
    @FXML private Label lblDate;
    @FXML private TextArea txtContent;

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
}
