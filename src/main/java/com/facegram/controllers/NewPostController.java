package com.facegram.controllers;

import com.facegram.model.DAO.PostDAO;
import com.facegram.model.dataobject.Post;
import com.facegram.model.dataobject.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class NewPostController {

    private User user;

    public NewPostController(User user){
        this.user=user;
        System.out.println("User en newPOst"+this.user);
    }

    public NewPostController(){}

    @FXML private TextArea txtNewContent;

    @FXML
    private void publishPost(){
        if(this.txtNewContent.equals("")) {
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Calendar calendar = Calendar.getInstance();
            Date dateNow = calendar.getTime();
            //String formattedDate = sdf.format(dateNow);
            new PostDAO(new Post(this.txtNewContent.getText(), dateNow, dateNow, user)).insert();
        }
    }
}
