package com.facegram.controllers;

import com.facegram.model.DAO.CommentDAO;
import com.facegram.model.dataobject.Comment;
import com.facegram.utils.message.ConfirmMessage;
import com.facegram.utils.message.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewCommentController {
    CommentDAO cDao = new CommentDAO();

    @FXML private TextArea textComment;
    @FXML private Button buttonClose;
    @FXML private Button buttonAdd;


    @FXML
    private void addComment() {
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
            switchToComment();
        }
    }

    @FXML
    private void switchToComment() {

    }
}
