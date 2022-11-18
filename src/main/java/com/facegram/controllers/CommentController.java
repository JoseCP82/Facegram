package com.facegram.controllers;

import com.facegram.App;
import com.facegram.logging.Logging;
import com.facegram.model.DAO.CommentDAO;
import com.facegram.model.dataobject.Comment;
import com.facegram.utils.message.ConfirmMessage;
import com.facegram.utils.message.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class CommentController {
    CommentDAO cDao = new CommentDAO();

    @FXML private Button addComment;
    @FXML private TextArea textComment;
    @FXML private Button buttonClose;
    @FXML private Button buttonAdd;

    @FXML
    private void newComment(){

    }

    @FXML
    private void addButton() {
        String text = textComment.getText();
        cDao.setText(text);

        cDao.insert();
    }

    @FXML
    private void cancelButton() {
        Message msg = new ConfirmMessage("¿Estás seguro?");
        msg.showMessage();
        if(((ConfirmMessage) msg).getBt() == ButtonType.OK) {
            Stage stage = (Stage) this.buttonClose.getScene().getWindow();
            stage.close();
            switchToFeed();
        }
    }

    @FXML
    private void switchToFeed() {

    }

}
