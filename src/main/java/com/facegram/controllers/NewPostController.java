package com.facegram.controllers;

import com.facegram.model.DAO.PostDAO;
import com.facegram.model.dataobject.Post;
import com.facegram.model.dataobject.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewPostController {

    @FXML
    private TextArea txtContent;



    private User user;

    public NewPostController(User user){
        this.user=user;
    }


    @FXML
    private void publishPost(){

        if(this.txtContent.equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Calendar calendar = Calendar.getInstance();
            Date dateNow = calendar.getTime();
            //String formattedDate = sdf.format(dateNow);
            new PostDAO(new Post(this.txtContent.getText(), dateNow, dateNow, user)).insert();
        }


    }

    @FXML
    private void closePublishWindow() {

    }


}
