package com.facegram.controllers;

import javafx.fxml.FXML;

import java.awt.*;

public class PostController {

    @FXML private Label lblUser;
    @FXML private Label lblDate;
    @FXML private TextArea txtContent;

    public void setPost(String userName, String content, String date) {
        this.lblUser.setText(userName);
        this.txtContent.setText(content);
        this.lblDate.setText(date);
    }
}
